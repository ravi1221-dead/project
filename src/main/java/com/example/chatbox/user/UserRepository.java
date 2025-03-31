package com.example.chatbox.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Chatbox methods
    List<User> findAllByStatus(Status status);

    // Login methods
    User findByEmail(String email);

    boolean existsByEmail(String email);  // ✅ Check if email already exists
}
