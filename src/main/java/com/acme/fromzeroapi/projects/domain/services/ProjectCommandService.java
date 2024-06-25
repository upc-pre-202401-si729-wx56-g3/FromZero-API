package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.AssignProjectDeveloperCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectProgressCommand;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectCandidatesListCommand command);
    Optional<Project> handle(AssignProjectDeveloperCommand command);
    Optional<Project> handle(UpdateProjectProgressCommand command);
}
