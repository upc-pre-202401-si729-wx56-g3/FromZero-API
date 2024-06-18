package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;

import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.SignUpDeveloperResource;

public class DeveloperCommandFromSignUpDeveloperResourceAssembler {
    public static SignUpDeveloperCommand toCommandFromResource(SignUpDeveloperResource signUpDeveloperResource) {
        return new SignUpDeveloperCommand(signUpDeveloperResource.mail(), signUpDeveloperResource.password(), signUpDeveloperResource.firstName(), signUpDeveloperResource.lastName());
    }
}
