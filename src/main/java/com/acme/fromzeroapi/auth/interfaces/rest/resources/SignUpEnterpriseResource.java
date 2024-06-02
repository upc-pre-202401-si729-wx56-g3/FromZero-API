package com.acme.fromzeroapi.auth.interfaces.rest.resources;

public record SignUpEnterpriseResource(
        String mail,
        String password,
        String enterpriseName
) {
}
