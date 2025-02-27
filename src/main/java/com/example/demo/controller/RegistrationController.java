package com.example.demo.controller;

import com.example.demo.model.MyAppUser;
import com.example.demo.model.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private MyAppUserService myAppUserService;

    // Sign Up Endpoint
    @PostMapping("/signup")
    public ResponseEntity<MyAppUser> signUp(@RequestBody MyAppUser user) {
    System.out.println("Received Signup Request: " + user);
    MyAppUser savedUser = myAppUserService.signUp(user);
    System.out.println("Saved User: " + savedUser);
    return ResponseEntity.ok(savedUser);
}


    // Sign In Endpoint
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = myAppUserService.signIn(email, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}

