package com.acme.fromzeroapi.home.application.commandservices;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import com.acme.fromzeroapi.home.domain.model.commands.CreateDesarrolladorCommand;
import com.acme.fromzeroapi.home.domain.services.DesarrolladorCommandService;
import com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories.DesarrolladorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DesarrolladorCommandServiceImpl implements DesarrolladorCommandService { // Impl = implementation

    private final DesarrolladorRepository desarrolladorRepository;

    public DesarrolladorCommandServiceImpl(DesarrolladorRepository desarrolladorRepository) {
        this.desarrolladorRepository = desarrolladorRepository;
    }

    @Override
    public Optional<Desarrollador> handle(CreateDesarrolladorCommand command) {

        var desarrollador = new Desarrollador(command);
        var createdDesarrollador = desarrolladorRepository.save(desarrollador);
        return Optional.of(createdDesarrollador);
    }

}