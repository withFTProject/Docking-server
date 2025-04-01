package com.lgcns.Docking.planet.repository;

import com.lgcns.Docking.planet.entity.Letter;
import com.lgcns.Docking.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    boolean existsByUser(User user);
}

