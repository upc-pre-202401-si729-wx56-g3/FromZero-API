package com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FrameworksRepository extends JpaRepository<Framework, Integer> {
    Optional<Framework> findFrameworkById(Integer id);
}
