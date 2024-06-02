package com.acme.fromzeroapi.developer_branch_projects.domain.services;

import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;

import java.util.Optional;

public interface DeveloperQueryService {
    Optional<Developer> handle(GetDeveloperByIdQuery query);
}
