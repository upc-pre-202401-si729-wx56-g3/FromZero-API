package com.acme.fromzeroapi.usermanagement.domain.services;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.usermanagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Developer> handle(GetAllDevelopersAsyncQuery query);
    Optional<Developer> handle(GetDeveloperByUserIdAsyncQuery query);
    Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query);
    Optional<Enterprise> handle(GetEnterpriseByIdQuery query);
    Optional<Developer> handle(GetDeveloperByIdQuery query);
}
