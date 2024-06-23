package com.acme.fromzeroapi.auth.application.internal.commandservices;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.domain.model.commands.CreateUserCommand;
import com.acme.fromzeroapi.auth.domain.services.UserCommandService;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        String email = command.email();
        userRepository.findByEmail(email).map(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });
        var user = new User(command);
        userRepository.save(user);
        return Optional.of(user);
    }
}
