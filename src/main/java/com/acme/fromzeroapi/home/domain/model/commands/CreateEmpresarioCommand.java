package com.acme.fromzeroapi.home.domain.model.commands;

public record CreateEmpresarioCommand(String country, int businessName, int cellphone, String email, String website, String section) {
}