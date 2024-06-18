package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.AuthenticateUserResource;

public class AuthenticatedUsedResourcerFromEntityAssembler {
    public static AuthenticateUserResource toResourceFromEntity(User user) {
        return new AuthenticateUserResource(user.getUserId(),user.getUserType());
    }
}
