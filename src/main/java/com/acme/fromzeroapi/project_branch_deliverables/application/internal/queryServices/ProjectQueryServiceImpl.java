package com.acme.fromzeroapi.project_branch_deliverables.application.internal.queryServices;

import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import com.acme.fromzeroapi.project_branch_deliverables.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.project_branch_deliverables.domain.services.ProjectQueryService;
import com.acme.fromzeroapi.project_branch_deliverables.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectRepository projectRepository;
    public ProjectQueryServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return this.projectRepository.findById(query.projectId());
    }


}
