package com.acme.fromzeroapi.home.application.commandservices;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.home.domain.model.commands.CreateEnterpriseCommand;
import com.acme.fromzeroapi.home.domain.services.EnterpriseCommandService;
import com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseCommandServiceImpl implements EnterpriseCommandService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseCommandServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Enterprise> handle(CreateEnterpriseCommand command) {
        var enterprise = new Enterprise(command);
        var createdEnterprise = enterpriseRepository.save(enterprise);
        return Optional.of(createdEnterprise);
    }
}