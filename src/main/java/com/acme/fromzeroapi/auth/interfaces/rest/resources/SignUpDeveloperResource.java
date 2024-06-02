package com.acme.fromzeroapi.auth.interfaces.rest.resources;

public record SignUpDeveloperResource(
        String mail,
        String password,
        String firstName,
        String lastName
) {
}
