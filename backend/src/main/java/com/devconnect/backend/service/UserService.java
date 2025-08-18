package com.devconnect.backend.service;

import com.devconnect.backend.entity.User;
import com.devconnect.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // This is Dependency Injection via the constructor
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String fullName, String email, String password) {
        // TODO: Check if email already exists
        if(userRepository.findByEmail(email).isPresent()){
            throw new IllegalStateException("Email already in use");
        }
        // TODO: Add password hashing for security
        String hashedPassword  = passwordEncoder.encode(password);


        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPasswordHash(hashedPassword);// We will hash this later

        // Use the repository to save the new user to the database
        return userRepository.save(newUser);
    }
}