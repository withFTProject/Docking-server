package com.lgcns.Docking.planet.service;

import com.lgcns.Docking.planet.dto.PlanetListResponseDto;
import com.lgcns.Docking.planet.entity.Letter;
import com.lgcns.Docking.planet.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    public Letter savePlanetToLetter(Long letterId, String planetUrl) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new IllegalArgumentException("편지를 찾을 수 없습니다."));
        letter.setPlanet(planetUrl);
        return letterRepository.save(letter);
    }

    public Page<PlanetListResponseDto> getPlanetList(int page, int size) {
        Page<Letter> letters = letterRepository.findAll(PageRequest.of(page, size));
        return letters.map(letter -> new PlanetListResponseDto(letter.getPlanet()));
    }
}
