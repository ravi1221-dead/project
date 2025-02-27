package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")  // This specifies the MongoDB collection name
public class MyAppUser {

    @Id
    private String id;   // MongoDB uses String (ObjectId) for IDs

    private String name;
    private String email;
    private String password;
}
