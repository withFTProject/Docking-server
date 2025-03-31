package com.lgcns.Docking.planet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "letter")
@Getter
@Setter
@NoArgsConstructor
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column
    private String planet;

    @Column(nullable = false)
    private String sticker;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
