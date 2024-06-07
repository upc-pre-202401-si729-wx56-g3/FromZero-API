package com.acme.fromzeroapi.home.application.commandservices;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.home.domain.model.commands.CreateDeveloperCommand;
import com.acme.fromzeroapi.home.domain.services.DeveloperCommandService;
import com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperCommandServiceImpl implements DeveloperCommandService { // Impl = implementation

    private final DeveloperRepository developerRepository;

    public DeveloperCommandServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Optional<Developer> handle(CreateDeveloperCommand command) {
        var developer = new Developer(command);
        var createdDesarrollador = developerRepository.save(developer);
        return Optional.of(createdDesarrollador);
    }

}