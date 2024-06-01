package com.acme.fromzeroapi.enterprise.domain.services;

import com.acme.fromzeroapi.enterprise.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.enterprise.domain.model.queries.GetEnterpriseByIdQuery;

import java.util.Optional;

public interface EnterpriseQueryService {
    Optional<Enterprise> handle(GetEnterpriseByIdQuery query);
}
