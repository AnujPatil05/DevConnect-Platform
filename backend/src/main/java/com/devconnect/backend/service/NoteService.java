package com.devconnect.backend.service;

import com.devconnect.backend.dto.NoteDto;
import com.devconnect.backend.entity.Note;
import com.devconnect.backend.entity.User;
import com.devconnect.backend.repository.NoteRepository;
import com.devconnect.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.devconnect.backend.dto.NoteResponseDto;
import com.devconnect.backend.dto.UserSummaryDto;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteResponseDto createNote(NoteDto noteDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        Note newNote = new Note();
        newNote.setTitle(noteDto.getTitle());
        newNote.setContent(noteDto.getContent());
        newNote.setUser(user);

        Note savedNote = noteRepository.save(newNote);

        // --- Map the result to our new DTOs ---
        UserSummaryDto authorDto = new UserSummaryDto();
        authorDto.setId(user.getId());
        authorDto.setFullName(user.getFullName());
        authorDto.setEmail(user.getEmail());

        NoteResponseDto responseDto = new NoteResponseDto();
        responseDto.setId(savedNote.getId());
        responseDto.setTitle(savedNote.getTitle());
        responseDto.setContent(savedNote.getContent());
        responseDto.setCreatedAt(savedNote.getCreatedAt());
        responseDto.setAuthor(authorDto);

        return responseDto;
    }


}