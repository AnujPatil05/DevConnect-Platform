package com.devconnect.backend.dto;

import lombok.Data;

@Data
public class UserSummaryDto {
    private Long id;
    private String fullName;
    private String email;
}