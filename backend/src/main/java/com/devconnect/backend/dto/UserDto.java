package com.devconnect.backend.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private Instant createdAt;
}