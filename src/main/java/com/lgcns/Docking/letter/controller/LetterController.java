package com.lgcns.Docking.letter.controller;

import com.lgcns.Docking.letter.dto.LetterCreateDTO;
import com.lgcns.Docking.letter.dto.LetterEditDTO;
import com.lgcns.Docking.letter.dto.LetterReadResponseDTO;
import com.lgcns.Docking.letter.dto.LetterResponseDto;
import com.lgcns.Docking.letter.service.LetterService;
import com.lgcns.Docking.user.dto.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import com.lgcns.Docking.letter.entity.Letter;

@RestController
@RequestMapping("/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

//    @PostMapping
//    public ResponseEntity<Void> createLetter(@RequestBody LetterCreateDTO dto, Authentication authentication) {
//        String username = ((CustomOAuth2User) authentication.getPrincipal()).getId();
//        letterService.createLetter(dto, username);
//        return ResponseEntity.ok().build();
//    }
    @PostMapping
    public ResponseEntity<LetterResponseDto> createLetter(@RequestBody LetterCreateDTO dto, Authentication authentication) {
        String username = ((CustomOAuth2User) authentication.getPrincipal()).getId();

        // Letter 객체를 저장하고 반환 받음
        Letter savedLetter = letterService.createLetter(dto, username);

        // 저장된 Letter의 ID로 응답 DTO 생성
        LetterResponseDto responseDto = new LetterResponseDto(savedLetter.getId(), "성공입니다");

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LetterReadResponseDTO> getLetter(@PathVariable Long id) {
        return ResponseEntity.ok(letterService.getLetter(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editLetter(@PathVariable Long id, @RequestBody LetterEditDTO dto) {
        letterService.editLetter(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLetter(@PathVariable Long id) {
        letterService.deleteLetter(id);
        return ResponseEntity.noContent().build();
    }
}


