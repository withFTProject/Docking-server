package com.lgcns.Docking.letter.service;

import com.lgcns.Docking.letter.dto.LetterCreateDTO;
import com.lgcns.Docking.letter.dto.LetterEditDTO;
import com.lgcns.Docking.letter.dto.LetterReadResponseDTO;
import com.lgcns.Docking.letter.entity.Letter;
import com.lgcns.Docking.letter.entity.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public void createLetter(LetterCreateDTO dto) {
        Letter letter = Letter.of(dto.getTitle(), dto.getContent());
        letterRepository.save(letter);
    }

    public LetterReadResponseDTO getLetter(Long id) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 편지가 없습니다."));
        return new LetterReadResponseDTO(letter.getTitle(), letter.getContent(), letter.getCreatedBy());
    }

    public void editLetter(Long id, LetterEditDTO dto) {
        Letter letter = letterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 편지가 없습니다."));
        letter.setTitle(dto.getTitle());
        letter.setContent(dto.getContent());
    }
}

