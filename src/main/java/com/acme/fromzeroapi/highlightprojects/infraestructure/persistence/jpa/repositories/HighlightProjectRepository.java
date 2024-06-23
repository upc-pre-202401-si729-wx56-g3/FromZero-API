package com.acme.fromzeroapi.highlightprojects.infraestructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.highlightprojects.domain.model.aggregates.HighlightProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighlightProjectRepository extends JpaRepository<HighlightProject, Long>{
}
