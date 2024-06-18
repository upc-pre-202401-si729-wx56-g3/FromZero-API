package com.acme.fromzeroapi.auth.application.internal.queryservices;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.queries.GetAllDevelopersAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetDeveloperByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final DeveloperRepository developerRepository;
    private final EnterpriseRepository enterpriseRepository;

    public ProfileQueryServiceImpl(DeveloperRepository developerRepository, EnterpriseRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public List<Developer> handle(GetAllDevelopersAsyncQuery query) {
        return developerRepository.findAll();
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByUserIdAsyncQuery query) {
        return developerRepository.findDeveloperByUserUserId(query.id());
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query) {
        return enterpriseRepository.findEnterpriseByUserUserId(query.id());
    }
}
