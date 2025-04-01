package com.lgcns.Docking.letter.controller;

import com.lgcns.Docking.letter.dto.LetterCreateDTO;
import com.lgcns.Docking.letter.dto.LetterEditDTO;
import com.lgcns.Docking.letter.dto.LetterReadResponseDTO;
import com.lgcns.Docking.letter.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/letter")
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

    @PostMapping
    public ResponseEntity<Void> createLetter(@RequestBody LetterCreateDTO dto) {
        letterService.createLetter(dto);
        return ResponseEntity.ok().build();
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
}


