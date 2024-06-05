package com.acme.fromzeroapi.auth.interfaces.rest.resources;


import com.acme.fromzeroapi.auth.domain.model.aggregates.User;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getUserId(), user.getUserType());
    }
}
