package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpDeveloperCommand;

public class DeveloperEntityFromResourceAssembler {
    public static Developer toDeveloperFromCommand(SignUpDeveloperCommand command, User user){
        return new Developer(
                user,
                command.firstName(),
                command.lastName(),
                "No description provided.",
                "No country provided.",
                "No phone provided.",
                0,
                "No specialties provided.",
                "https://cdn-icons-png.flaticon.com/512/3237/3237472.png"
        );
    }
}
