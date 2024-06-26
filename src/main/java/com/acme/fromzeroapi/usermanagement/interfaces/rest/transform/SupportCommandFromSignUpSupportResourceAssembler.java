package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;

import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpDeveloperCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpSupportCommand;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.SignUpDeveloperResource;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.SignUpSupportResource;

public class SupportCommandFromSignUpSupportResourceAssembler {
    public static SignUpSupportCommand toCommandFromResource(SignUpSupportResource signUpSupportResource) {
        return new SignUpSupportCommand(signUpSupportResource.mail(), signUpSupportResource.password());
    }
}
