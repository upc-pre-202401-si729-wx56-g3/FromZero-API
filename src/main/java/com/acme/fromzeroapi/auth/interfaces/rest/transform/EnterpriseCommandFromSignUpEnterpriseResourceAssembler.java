package com.acme.fromzeroapi.auth.interfaces.rest.transform;

import com.acme.fromzeroapi.auth.domain.model.commands.SignUpEnterpriseCommand;
import com.acme.fromzeroapi.auth.interfaces.rest.resources.SignUpEnterpriseResource;

public class EnterpriseCommandFromSignUpEnterpriseResourceAssembler {
    public static SignUpEnterpriseCommand toCommandFromResource(SignUpEnterpriseResource signUpEnterpriseResource) {
        return new SignUpEnterpriseCommand(signUpEnterpriseResource.mail(), signUpEnterpriseResource.password(), signUpEnterpriseResource.enterpriseName());
    }
}
