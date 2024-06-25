package com.acme.fromzeroapi.usermanagement.domain.services;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Developer> handle(UpdateDeveloperCompletedProjectsCommand command);

}
