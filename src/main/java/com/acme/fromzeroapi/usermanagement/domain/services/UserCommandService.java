package com.acme.fromzeroapi.usermanagement.domain.services;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignInCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpEnterpriseCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpSupportCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpDeveloperCommand command);
    Optional<User> handle(SignUpEnterpriseCommand command);
    Optional<User> handle(SignUpSupportCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
