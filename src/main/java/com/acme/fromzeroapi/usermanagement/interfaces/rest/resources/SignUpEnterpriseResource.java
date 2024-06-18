package com.acme.fromzeroapi.usermanagement.interfaces.rest.resources;

public record SignUpEnterpriseResource(
        String mail,
        String password,
        String enterpriseName
) {
}
