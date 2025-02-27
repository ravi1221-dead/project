package com.example.demo.model;

import com.example.demo.model.MyAppUser;
import com.example.demo.model.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyAppUserService {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    // Sign Up Method (Registers a new user)
    public MyAppUser signUp(MyAppUser user) {
        System.out.println("Before Saving: " + user);
        MyAppUser savedUser = myAppUserRepository.save(user);
        System.out.println("After Saving: " + savedUser);
        return savedUser;
    }

    // Sign In Method (Validates login credentials)
    public boolean signIn(String email, String password) {
        Optional<MyAppUser> user = myAppUserRepository.findByEmail(email);
        System.out.println("User Found: " + user);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
