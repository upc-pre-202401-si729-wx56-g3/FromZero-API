package com.acme.fromzeroapi.projects.application.internal.queryServices;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.queries.GetFrameworkByIdQuery;
import com.acme.fromzeroapi.projects.domain.services.FrameworksQueryService;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.FrameworksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FrameworkQueryServiceImpl implements FrameworksQueryService {

    private final FrameworksRepository frameworksRepository;

    public FrameworkQueryServiceImpl(FrameworksRepository frameworksRepository) {
        this.frameworksRepository = frameworksRepository;
    }

    @Override
    public Optional<Framework> handle(GetFrameworkByIdQuery query) {
        return this.frameworksRepository.findFrameworkById(query.id());
    }
}
