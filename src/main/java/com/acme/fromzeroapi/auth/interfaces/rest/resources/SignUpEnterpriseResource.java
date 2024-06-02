package com.acme.fromzeroapi.auth.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignUpEnterpriseResource {
    @NotBlank
    @Email
    private String mail;
    @NotBlank
    private String password;
    @NotBlank
    private String enterpriseName;
    private String description = "No description provided.";
    private String country = "No country provided.";
    private String ruc = "No RUC provided.";
    private String phone = "No phone provided.";
    private String website = "No website provided.";
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";
    private String sector = "No sector provided.";
}
