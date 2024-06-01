package com.acme.fromzeroapi.projects.application.internal.queryServices;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsByStateQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {
    private final ProjectRepository projectRepository;
    public ProjectQueryServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> handle(GetAllProjectsQuery query) {
        return this.projectRepository.findAll();
    }

    @Override
    public List<Project> handle(GetAllProjectsByStateQuery query) {
        return this.projectRepository.findAllByState(query.state());
    }

    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return this.projectRepository.findById(query.id());
    }
}
