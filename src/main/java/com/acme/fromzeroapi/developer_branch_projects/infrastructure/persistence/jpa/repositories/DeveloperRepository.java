package com.acme.fromzeroapi.developer_branch_projects.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
}
