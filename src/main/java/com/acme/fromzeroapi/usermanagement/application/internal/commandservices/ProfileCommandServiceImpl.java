package com.acme.fromzeroapi.usermanagement.application.internal.commandservices;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperProfileCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateEnterpriseProfileCommand;
import com.acme.fromzeroapi.usermanagement.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.usermanagement.infraestructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.usermanagement.infraestructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final DeveloperRepository developerRepository;
    private final EnterpriseRepository enterpriseRepository;

    public ProfileCommandServiceImpl(DeveloperRepository developerRepository, EnterpriseRepository enterpriseRepository) {
        this.developerRepository = developerRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperCompletedProjectsCommand command) {
        var updatedDeveloper = command.developer();
        updatedDeveloper.setCompletedProjects(command.addProject());
        this.developerRepository.save(updatedDeveloper);
        return Optional.of(updatedDeveloper);
    }

    @Override
    public Optional<Developer> handle(UpdateDeveloperProfileCommand command) {
        var developer = developerRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Developer with id " + command.id() + " not found"));

        developer.setDescription(command.description());
        developer.setCountry(command.country());
        developer.setPhone(command.phone());
        developer.setSpecialties(command.specialties());
        developer.setProfileImgUrl(command.profileImgUrl());

        developerRepository.save(developer);

        return Optional.of(developer);
    }

    @Override
    public Optional<Enterprise> handle(UpdateEnterpriseProfileCommand command) {
        var enterprise= enterpriseRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Enterprise with id " + command.id() + " not found"));
        enterprise.setDescription(command.description());
        enterprise.setCountry(command.country());
        enterprise.setRuc(command.ruc());
        enterprise.setPhone(command.phone());
        enterprise.setWebsite(command.website());
        enterprise.setProfileImgUrl(command.profileImgUrl());
        enterprise.setSector(command.sector());

        enterpriseRepository.save(enterprise);

        return Optional.of(enterprise);
    }

}
