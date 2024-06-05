package com.acme.fromzeroapi.home.domain.services;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import com.acme.fromzeroapi.home.domain.model.commands.CreateDesarrolladorCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface DesarrolladorCommandService{
    Optional<Desarrollador> handle(CreateDesarrolladorCommand command);
}
