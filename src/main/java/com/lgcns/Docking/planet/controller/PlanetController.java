package com.lgcns.Docking.planet.controller;

import com.lgcns.Docking.planet.dto.PlanetListResponseDto;
import com.lgcns.Docking.planet.dto.PlanetRequestDto;
import com.lgcns.Docking.planet.dto.PlanetResponseDto;
import com.lgcns.Docking.letter.entity.Letter;
import com.lgcns.Docking.letter.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor
public class PlanetController {

    private final LetterService letterService;

    // 1. 행성 선택 (편지에 행성 이미지 URL 저장)
    @PutMapping("/{letterId}")
    public ResponseEntity<PlanetResponseDto> choosePlanet(
            @PathVariable Long letterId,
            @RequestBody PlanetRequestDto planetRequestDto
    ) {
        Letter updated = letterService.savePlanetToLetter(letterId, planetRequestDto.getPlanetUrl());
        return ResponseEntity.ok(new PlanetResponseDto(
                updated.getId(),
                "성공입니다"
        ));
    }

    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> getPlanetList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    ) {
        Page<PlanetListResponseDto> resultPage = letterService.getPlanetList(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("code", "COMMON200");
        response.put("message", "성공입니다.");

        Map<String, Object> result = new HashMap<>();
        result.put("content", resultPage.getContent());
        result.put("page", resultPage.getNumber());
        result.put("size", resultPage.getSize());
        result.put("totalPages", resultPage.getTotalPages());
        result.put("totalElements", resultPage.getTotalElements());

        response.put("result", result);

        return ResponseEntity.ok(response);
    }
}
