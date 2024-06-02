package com.acme.fromzeroapi.auth.domain.model.commands;

public record SignUpEnterpriseCommand(
        CreateUserCommand createUserCommand,
        String enterpriseName
) {
        public SignUpEnterpriseCommand(String mail, String password, String enterpriseName) {
                this(new CreateUserCommand(mail, password, "E"), enterpriseName);
        }

        public boolean isOfType(Class<?> type) {
                return type.isInstance(createUserCommand);
        }
}
