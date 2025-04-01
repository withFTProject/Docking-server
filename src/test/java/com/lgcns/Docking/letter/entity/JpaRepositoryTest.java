package com.lgcns.Docking.letter.entity;

import com.lgcns.Docking.letter.config.JpaConfig;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final LetterRepository letterRepository;

    public JpaRepositoryTest(@Autowired LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    @DisplayName("selcet 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        //Givne

        //When
        List<Letter> letters = letterRepository.findAll();

        //Then
        assertThat(letters)
                .isNotNull()
                .hasSize(123);
    }
}