package com.acme.fromzeroapi.auth.domain.model.commands;

public record CreateUserCommand(
        String email,
        String password,
        String userType) {
}
