package com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findEnterpriseByUserUserId(Long userId);
}
