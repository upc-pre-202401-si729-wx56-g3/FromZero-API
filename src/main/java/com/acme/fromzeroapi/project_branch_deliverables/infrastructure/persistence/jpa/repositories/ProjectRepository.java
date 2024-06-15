package com.acme.fromzeroapi.project_branch_deliverables.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
