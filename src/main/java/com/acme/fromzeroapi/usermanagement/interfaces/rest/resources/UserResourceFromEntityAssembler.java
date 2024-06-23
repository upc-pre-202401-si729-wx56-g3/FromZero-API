package com.acme.fromzeroapi.usermanagement.interfaces.rest.resources;


import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getUserId(), user.getUserType());
    }
}
