package com.acme.fromzeroapi.usermanagement.application.internal.commandservices;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperProfileCommand;
import com.acme.fromzeroapi.usermanagement.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.usermanagement.infraestructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final DeveloperRepository developerRepository;

    public ProfileCommandServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
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

}
