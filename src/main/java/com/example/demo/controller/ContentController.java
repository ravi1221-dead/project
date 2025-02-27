package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/req")  // Base path for all endpoints
public class ContentController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Ensure login.html is inside src/main/resources/templates
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";  // Ensure signup.html is inside src/main/resources/templates
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "index";  // Ensure index.html is inside src/main/resources/templates
    }
}
