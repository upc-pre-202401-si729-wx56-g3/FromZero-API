package com.acme.fromzeroapi.developer_branch_projects.application.internal.queryServices;

import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.DeveloperRepository;
//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.developer_branch_projects.domain.services.DeveloperQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperQueryServiceImpl implements DeveloperQueryService {
    private final DeveloperRepository developerRepository;
    public DeveloperQueryServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByIdQuery query) {
        return this.developerRepository.findById(query.id());
    }
}
