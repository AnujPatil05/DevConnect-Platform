package com.devconnect.backend.controller;

import com.devconnect.backend.dto.UserRegistrationDto;
import com.devconnect.backend.entity.User;
import com.devconnect.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Import this
import org.springframework.http.ResponseEntity; // Import this
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationDto registrationDto) {
        return userService.registerUser(
                registrationDto.getFullName(),
                registrationDto.getEmail(),
                registrationDto.getPassword()
        );
    }

    // ADD THIS NEW METHOD
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        // Return a 400 Bad Request status with the exception message in the body
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}