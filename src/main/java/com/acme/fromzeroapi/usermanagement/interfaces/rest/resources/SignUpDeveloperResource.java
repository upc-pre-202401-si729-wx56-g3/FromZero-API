package com.acme.fromzeroapi.usermanagement.interfaces.rest.resources;

public record SignUpDeveloperResource(
        String mail,
        String password,
        String firstName,
        String lastName
) {
}
