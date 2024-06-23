package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;

import com.acme.fromzeroapi.usermanagement.domain.model.commands.SignUpEnterpriseCommand;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.SignUpEnterpriseResource;

public class EnterpriseCommandFromSignUpEnterpriseResourceAssembler {
    public static SignUpEnterpriseCommand toCommandFromResource(SignUpEnterpriseResource signUpEnterpriseResource) {
        return new SignUpEnterpriseCommand(signUpEnterpriseResource.mail(), signUpEnterpriseResource.password(), signUpEnterpriseResource.enterpriseName());
    }
}
