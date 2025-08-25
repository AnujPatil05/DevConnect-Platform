package com.devconnect.backend.service;

import com.devconnect.backend.dto.UserDto;
import com.devconnect.backend.entity.User;
import com.devconnect.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devconnect.backend.dto.UserDto;
import org.springframework.security.authentication.BadCredentialsException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // This is Dependency Injection via the constructor
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDto registerUser(String fullName, String email, String password) {
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
        User savedUser = userRepository.save(newUser);

        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setCreatedAt(savedUser.getCreatedAt());

        return userDto;
    }

    public String login(String email, String password){

        User user = userRepository.findByEmail(email).orElseThrow( () -> new BadCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new BadCredentialsException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail());
    }

}