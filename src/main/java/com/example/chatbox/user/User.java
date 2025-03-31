package com.example.chatbox.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users") // Ensuring both login & chat use the same DB collection
public class User {

    @Id
    private String id;  // ✅ Unique ID for MongoDB

    private String nickName;  // ✅ Chatbox Field (Kept as it is)
    private String fullName;  // ✅ Chatbox Field (Kept as it is)
    private Status status;  // ✅ Chatbox Field (Kept as it is, ONLINE/OFFLINE)

    private String username;  // ✅ Added from Login System
    private String email;  // ✅ Added from Login System

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!*]).{8,}$",
            message = "Password must include at least one uppercase, one lowercase, one number, and one special character.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;  // ✅ Added from Login System

    private String confirmPassword;  // ✅ Added from Login System

    @JsonIgnore
    private String role;  // ✅ Added from Login System (`NORMAL` or `CONFIDANT`)

    // ✅ Getter & Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ✅ Getter & Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ✅ Getter & Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ Getter & Setter for confirmPassword
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // ✅ Getter & Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
