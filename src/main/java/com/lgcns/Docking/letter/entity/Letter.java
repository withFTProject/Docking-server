package com.lgcns.Docking.letter.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdBy")
}
)

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Column(nullable = false) private String title; //제목
    @Setter @Column(nullable = false, length = 10000) private String content; //본문

    @CreatedBy @Column(nullable = false, updatable = false, length = 100) private String createdBy; //생성자

    protected Letter() {}

    private Letter(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Letter of (String title, String content) {
        return new Letter(title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Letter letter)) return false;
        return id == letter.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
