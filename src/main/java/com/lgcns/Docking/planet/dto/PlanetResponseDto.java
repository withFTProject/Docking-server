package com.lgcns.Docking.planet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlanetResponseDto {
    private Long letterId;
    private String planetUrl;
    private String message;
}
