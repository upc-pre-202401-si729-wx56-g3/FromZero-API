package com.acme.fromzeroapi.usermanagement.domain.services;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperProfileCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateEnterpriseProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Developer> handle(UpdateDeveloperCompletedProjectsCommand command);
    Optional<Developer> handle(UpdateDeveloperProfileCommand command);
    Optional<Enterprise> handle(UpdateEnterpriseProfileCommand command);
}
