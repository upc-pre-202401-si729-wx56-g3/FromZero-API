package com.acme.fromzeroapi.enterprise_branch_projects.application.internal.queryServices;

import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.EnterpriseRepository;
//import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.queries.GetEnterpriseByIdQuery;
import com.acme.fromzeroapi.enterprise_branch_projects.domain.services.EnterpriseQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseQueryServiceImpl implements EnterpriseQueryService {
    private final EnterpriseRepository enterpriseRepository;
    public EnterpriseQueryServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByIdQuery query) {
        return this.enterpriseRepository.findById(query.enterpriseId());
    }
}
