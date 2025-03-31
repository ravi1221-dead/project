package com.example.chatbox.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Chatbox methods
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }

    // ✅ Login/signup methods
    public User signUp(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists with this email!");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("NORMAL"); // Default role
        return repository.save(user);
    }

    public User login(String email, String password) {
        User foundUser = repository.findByEmail(email);

        if (foundUser == null || !passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new RuntimeException("Wrong credentials, please try again.");
        }

        return foundUser;
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean isPasswordValid(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}
