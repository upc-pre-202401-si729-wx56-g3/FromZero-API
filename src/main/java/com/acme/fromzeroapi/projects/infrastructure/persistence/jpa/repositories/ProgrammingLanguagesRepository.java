package com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrammingLanguagesRepository extends JpaRepository<ProgrammingLanguage, Integer> {
    Optional<ProgrammingLanguage> findProgrammingLanguageById(Integer id);
}
