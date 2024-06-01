package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsByStateQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;

import java.util.List;

public interface ProjectQueryService {
    List<Project> handle(GetAllProjectsQuery query);
    List<Project>handle(GetAllProjectsByStateQuery query);
}
