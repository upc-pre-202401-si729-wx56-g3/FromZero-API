package com.acme.fromzeroapi.auth.application.internal.commandservices;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.domain.model.commands.CreateUserCommand;
import com.acme.fromzeroapi.auth.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.auth.domain.services.UserCommandService;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final DeveloperRepository developerRepository;

    public UserCommandServiceImpl(UserRepository userRepository, DeveloperRepository developerRepository) {
        this.userRepository = userRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    public Optional<User> handle(SignUpDeveloperCommand command) {
        String email = command.createUserCommand().email();
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });

        User user = new User(new CreateUserCommand(
                email,
                command.createUserCommand().password(),
                command.createUserCommand().userType()
        ));
        userRepository.save(user);

        Developer developer = new Developer(
                user,
                command.firstName(),
                command.lastName(),
                "No description provided.",
                "No country provided.",
                "No phone provided.",
                0,
                "No specialties provided.",
                "https://cdn-icons-png.flaticon.com/512/3237/3237472.png"
        );
        developerRepository.save(developer);

        return Optional.of(user);
    }
}

