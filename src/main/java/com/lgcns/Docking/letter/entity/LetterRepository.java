package com.lgcns.Docking.letter.entity;

import com.lgcns.Docking.letter.entity.Letter;
import com.lgcns.Docking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    boolean existsByUser(User user);
}