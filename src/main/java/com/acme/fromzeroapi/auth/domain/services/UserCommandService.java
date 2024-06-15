package com.acme.fromzeroapi.auth.domain.services;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.domain.model.commands.CreateUserCommand;
import com.acme.fromzeroapi.auth.domain.model.commands.SignInCommand;
import com.acme.fromzeroapi.auth.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.auth.domain.model.commands.SignUpEnterpriseCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpDeveloperCommand command);
    Optional<User> handle(SignUpEnterpriseCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
