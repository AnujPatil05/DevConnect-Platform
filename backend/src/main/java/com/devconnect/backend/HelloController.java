package com.devconnect.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Tells Spring that this class will handle web requests
public class HelloController {

    @GetMapping("/api/hello") // Maps this method to the URL: /api/hello
    public String sayHello() {
        return "Hello from DevConnect!";
    }
}