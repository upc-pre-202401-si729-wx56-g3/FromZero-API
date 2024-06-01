package com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByState(String state);
}
