package com.lgcns.Docking.letter.dto;

public class LetterResponseDto {
    private Long letterId;
    private String message;

    // 기본 생성자
    public LetterResponseDto() {
    }

    // ID와 메시지를 받는 생성자
    public LetterResponseDto(Long letterId, String message) {
        this.letterId = letterId;
        this.message = message;
    }

    // 메시지만 받는 생성자
    public LetterResponseDto(String message) {
        this.message = message;
    }

    // getter와 setter
    public Long getLetterId() {
        return letterId;
    }

    public void setLetterId(Long letterId) {
        this.letterId = letterId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}