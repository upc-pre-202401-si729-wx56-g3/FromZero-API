package com.acme.fromzeroapi.auth.domain.services;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.domain.model.commands.CreateUserCommand;
import com.acme.fromzeroapi.auth.domain.model.commands.SignUpDeveloperCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpDeveloperCommand command);
}
