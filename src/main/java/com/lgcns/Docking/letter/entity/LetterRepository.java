package com.lgcns.Docking.letter.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface LetterRepository extends JpaRepository<Letter, Long> {
}
