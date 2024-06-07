package com.acme.fromzeroapi.home.application.queryservices;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllEnterprisesQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByCountryQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByIdQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByRucQuery;
import com.acme.fromzeroapi.home.domain.services.EnterpriseQueryService;
import com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseQueryServiceImpl implements EnterpriseQueryService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseQueryServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByIdQuery query) {
        return enterpriseRepository.findById(query.Id());
    }

    @Override
    public List<Enterprise> handle(GetEnterpriseByCountryQuery query) {
        return enterpriseRepository.findByCountry(query.country());
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByRucQuery query) {
        return enterpriseRepository.findByRuc(query.ruc());
    }

    @Override
    public List<Enterprise> handle(GetAllEnterprisesQuery query) {
        return enterpriseRepository.findAll();
    }
}