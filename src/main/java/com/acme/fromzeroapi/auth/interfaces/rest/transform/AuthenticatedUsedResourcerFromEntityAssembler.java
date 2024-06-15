package com.acme.fromzeroapi.auth.interfaces.rest.transform;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.interfaces.rest.resources.AuthenticateUserResource;

public class AuthenticatedUsedResourcerFromEntityAssembler {
    public static AuthenticateUserResource toResourceFromEntity(User user) {
        return new AuthenticateUserResource(user.getUserId());
    }
}
