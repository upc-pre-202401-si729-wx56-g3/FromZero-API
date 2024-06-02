package com.acme.fromzeroapi.auth.domain.model.commands;

public record CreateUserCommand(String password, String email, String userType) {
}
