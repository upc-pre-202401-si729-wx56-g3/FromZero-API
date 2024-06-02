package com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
