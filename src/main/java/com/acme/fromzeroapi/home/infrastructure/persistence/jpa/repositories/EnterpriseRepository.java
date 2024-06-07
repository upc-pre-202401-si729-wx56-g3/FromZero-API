package com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    Optional<Enterprise> findById(Long id);

    List<Enterprise> findByCountry(String country);

    Optional<Enterprise> findByRuc(String ruc);

    List<Enterprise> findAll();
}