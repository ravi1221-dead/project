package com.example.chatbox.user;

import com.example.chatbox.dto.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081/")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    // WebSocket Mappings for Chat Functionality
    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnectUser(@Payload User user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }

    // Login & Signup Mappings
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user, BindingResult result) {
        log.info("Received signup request: {}", user);

        if (result.hasErrors()) {
            log.error("Validation errors: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            log.error("Passwords do not match!");
            return ResponseEntity.badRequest().body("Password and Confirm Password do not match!");
        }

        try {
            User savedUser = userService.signUp(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            log.error("Signup error: {}", e.getMessage());
            return ResponseEntity.status(409).body(e.getMessage()); // 409 Conflict if user exists
        }
    }

    @PostMapping("/login/")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        log.info("Login request received for email: {}", request.getEmail());

        try {
            User user = userService.login(request.getEmail(), request.getPassword());

            Map<String, Object> response = Map.of(
                    "message", "Login successful!",
                    "user", Map.of(
                            "email", user.getEmail(),
                            "role", user.getRole()
                    ),
                    "redirect", "CONFIDANT".equals(user.getRole()) ? "/confidant-dashboard" : "/normal-dashboard"
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            log.error("Login error: {}", e.getMessage());
            return ResponseEntity.status(401).body(Map.of("message", "Wrong credentials, please try again."));
        }
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "API is working!";
    }
}
