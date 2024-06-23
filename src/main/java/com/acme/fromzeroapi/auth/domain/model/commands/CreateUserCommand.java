package com.acme.fromzeroapi.auth.domain.model.commands;

public record CreateUserCommand(String username, String password, String email) {
}
