package com.acme.fromzeroapi.projects.application.internal.commandServices;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {
    private final ProjectRepository projectRepository;
    public ProjectCommandServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        // ...
        var project= new Project(command);
        this.projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    public Optional<Project> handle(UpdateProjectCandidatesListCommand command) {
        var project = command.project();
        //var developer=command.developer();
        project.getCandidates().add(command.developer());
        //developer.getProjects().add(project);
        //System.out.println("Proyectos del developer: "+developer.getProjects());
        //System.out.println("El developer es: "+developer);
        //developer.getProjects().add(command.project());

        this.projectRepository.save(project);
        return Optional.of(project);
    }
}
