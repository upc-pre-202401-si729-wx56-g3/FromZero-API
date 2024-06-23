package com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Optional<Developer> findById(Long Id);

    Optional<Developer> findByCountry(String country);

    Optional<Developer> findBySpecialties(String specialties);

    List<Developer> findAll();
}
