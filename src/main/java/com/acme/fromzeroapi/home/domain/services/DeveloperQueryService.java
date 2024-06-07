package com.acme.fromzeroapi.home.domain.services;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllDevelopersQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperByCountryQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperBySpecialityQuery;


import java.util.List;
import java.util.Optional;

public interface DeveloperQueryService {
    Optional<Developer> handle(GetDeveloperByCountryQuery query);

    Optional<Developer> handle(GetDeveloperBySpecialityQuery query);

    Optional<Developer> handle(GetDeveloperByIdQuery query);

    List<Developer> handle(GetAllDevelopersQuery query);
}
