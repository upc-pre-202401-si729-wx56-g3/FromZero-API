package com.acme.fromzeroapi.home.domain.services;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllDesarrolladoresQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDesarrolladorByEmailQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDesarrolladorByIdQuery;


import java.util.List;
import java.util.Optional;

public interface DesarrolladorQueryService {
    Optional<Desarrollador> handle(GetDesarrolladorByIdQuery query);

    Optional<Desarrollador> handle(GetDesarrolladorByEmailQuery query);

    List<Desarrollador> handle(GetAllDesarrolladoresQuery query);
}
