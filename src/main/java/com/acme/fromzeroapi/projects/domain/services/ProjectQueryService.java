package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {
    List<Project> handle(GetAllProjectsQuery query);
    List<Project>handle(GetAllProjectsByStateQuery query);
    Optional<Project> handle(GetProjectByIdQuery query);
    List<Project> handle(GetAllProjectsByDeveloperIdQuery query);
    List<Project> handle(GetAllProjectsByEnterpriseIdQuery query);
}
