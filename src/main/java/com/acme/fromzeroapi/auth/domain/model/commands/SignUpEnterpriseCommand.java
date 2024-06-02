package com.acme.fromzeroapi.auth.domain.model.commands;

public record SignUpEnterpriseCommand(
        String mail,
        String password,
        String enterpriseName,
        String description,
        String country,
        String ruc,
        String phone,
        String website,
        String profileImgUrl,
        String sector
) {
}
