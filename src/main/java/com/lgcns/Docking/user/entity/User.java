package com.lgcns.Docking.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // OAuth2에서 제공하는 식별자

    private String name;

    private String profileImg = "default"; // 프로필 이미지 경로

    private String email;
}