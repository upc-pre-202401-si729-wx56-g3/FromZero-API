package com.acme.fromzeroapi.enterprise_branch_projects.domain.services;

import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.queries.GetEnterpriseByIdQuery;

import java.util.Optional;

public interface EnterpriseQueryService {
    Optional<Enterprise> handle(GetEnterpriseByIdQuery query);
}
