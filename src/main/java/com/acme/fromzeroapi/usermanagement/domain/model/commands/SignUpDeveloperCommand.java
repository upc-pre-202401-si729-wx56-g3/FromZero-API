package com.acme.fromzeroapi.usermanagement.domain.model.commands;

public record SignUpDeveloperCommand(
        CreateUserCommand createUserCommand,
        String firstName,
        String lastName
) {
        public SignUpDeveloperCommand(String mail, String password, String firstName, String lastName) {
                this(new CreateUserCommand(mail, password, "D"), firstName, lastName);
        }

        public boolean isOfType(Class<?> type) {
                return type.isInstance(createUserCommand);
        }
}