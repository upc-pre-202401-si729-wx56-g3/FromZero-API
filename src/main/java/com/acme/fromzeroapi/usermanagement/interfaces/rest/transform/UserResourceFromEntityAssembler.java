package com.acme.fromzeroapi.usermanagement.interfaces.rest.transform;


import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import com.acme.fromzeroapi.usermanagement.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getUserId(), user.getUserType());
    }
}
