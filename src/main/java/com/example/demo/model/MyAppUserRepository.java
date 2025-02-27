package com.example.demo.model;

import com.example.demo.model.MyAppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MyAppUserRepository extends MongoRepository<MyAppUser, String> {

    // Custom method to find a user by email and password (for login)
    Optional<MyAppUser> findByEmailAndPassword(String email, String password);

    // Custom method to find a user by email (useful for checking if email exists)
    Optional<MyAppUser> findByEmail(String email);
}

