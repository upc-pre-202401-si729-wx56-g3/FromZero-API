package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.queries.GetFrameworkByIdQuery;

import java.util.Optional;

public interface FrameworksQueryService {
    Optional<Framework> handle(GetFrameworkByIdQuery query);
}
