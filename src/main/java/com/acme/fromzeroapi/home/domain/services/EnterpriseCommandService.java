package com.acme.fromzeroapi.home.domain.services;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.home.domain.model.commands.CreateEnterpriseCommand;

import java.util.Optional;

public interface EnterpriseCommandService {
    Optional<Enterprise> handle(CreateEnterpriseCommand command);
}
