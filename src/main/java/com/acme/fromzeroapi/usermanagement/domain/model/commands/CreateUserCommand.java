package com.acme.fromzeroapi.usermanagement.domain.model.commands;

public record CreateUserCommand(
        String email,
        String password,
        String userType) {
}
