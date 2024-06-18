package com.acme.fromzeroapi.auth.domain.services;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.queries.GetAllDevelopersAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetDeveloperByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Developer> handle(GetAllDevelopersAsyncQuery query);
    Optional<Developer> handle(GetDeveloperByUserIdAsyncQuery query);
    Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query);
}
