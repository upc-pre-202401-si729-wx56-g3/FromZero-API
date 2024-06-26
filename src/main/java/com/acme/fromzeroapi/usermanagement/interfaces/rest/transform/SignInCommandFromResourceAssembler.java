package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;

import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignInCommand;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
