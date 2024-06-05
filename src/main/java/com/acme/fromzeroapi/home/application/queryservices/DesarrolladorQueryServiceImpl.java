package com.acme.fromzeroapi.home.application.queryservices;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllDesarrolladoresQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDesarrolladorByEmailQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDesarrolladorByIdQuery;
import com.acme.fromzeroapi.home.domain.services.DesarrolladorQueryService;
import com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories.DesarrolladorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesarrolladorQueryServiceImpl implements DesarrolladorQueryService {

    private final DesarrolladorRepository desarrolladorRepository;

    public DesarrolladorQueryServiceImpl(DesarrolladorRepository desarrolladorRepository) {
        this.desarrolladorRepository = desarrolladorRepository;
    }

    @Override
    public Optional<Desarrollador> handle(GetDesarrolladorByIdQuery query) {
        return Optional.empty();
    }

    @Override
    public Optional<Desarrollador> handle(GetDesarrolladorByEmailQuery query) {
        return Optional.empty();
    }

    @Override
    public List<Desarrollador> handle(GetAllDesarrolladoresQuery query) {
        return desarrolladorRepository.findAll();
    }
}
