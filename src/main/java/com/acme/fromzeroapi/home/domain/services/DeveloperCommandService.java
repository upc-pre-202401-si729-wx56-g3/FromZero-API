package com.acme.fromzeroapi.home.domain.services;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.home.domain.model.commands.CreateDeveloperCommand;

import java.util.Optional;

public interface DeveloperCommandService {
    Optional<Developer> handle(CreateDeveloperCommand command);
}
