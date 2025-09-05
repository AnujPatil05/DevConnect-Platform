package com.devconnect.backend.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class NoteResponseDto {
    private Long id;
    private UserSummaryDto author;
    private String title;
    private String content;
    private Instant createdAt;
}