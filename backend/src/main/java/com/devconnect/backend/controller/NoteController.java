package com.devconnect.backend.controller;

import com.devconnect.backend.dto.NoteResponseDto;
import com.devconnect.backend.dto.NoteDto;
import com.devconnect.backend.entity.Note;
import com.devconnect.backend.entity.User;
import com.devconnect.backend.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote(
            @RequestBody NoteDto noteDto,
            @AuthenticationPrincipal User user
    ) {
        NoteResponseDto createdNoteDto = noteService.createNote(noteDto, user.getEmail());
        return ResponseEntity.ok(createdNoteDto);
    }
}