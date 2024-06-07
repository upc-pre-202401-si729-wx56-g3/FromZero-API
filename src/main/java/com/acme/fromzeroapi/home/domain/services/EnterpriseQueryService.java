package com.acme.fromzeroapi.home.domain.services;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllEnterprisesQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByCountryQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByIdQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetEnterpriseByRucQuery;

import java.util.List;
import java.util.Optional;

public interface EnterpriseQueryService {
    Optional<Enterprise> handle(GetEnterpriseByIdQuery query);

    List<Enterprise> handle(GetEnterpriseByCountryQuery query);

    Optional<Enterprise> handle(GetEnterpriseByRucQuery query);

    List<Enterprise> handle(GetAllEnterprisesQuery query);
}
