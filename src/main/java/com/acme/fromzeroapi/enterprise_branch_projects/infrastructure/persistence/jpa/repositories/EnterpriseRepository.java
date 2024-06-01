package com.acme.fromzeroapi.enterprise_branch_projects.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
