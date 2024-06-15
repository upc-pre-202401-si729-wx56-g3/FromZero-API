package com.acme.fromzeroapi.project_branch_deliverables.domain.services;

import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import com.acme.fromzeroapi.project_branch_deliverables.domain.model.queries.GetProjectByIdQuery;

import java.util.Optional;


public interface ProjectQueryService {

    Optional<Project> handle(GetProjectByIdQuery query);
}
