package com.acme.fromzeroapi.home.domain.model.commands;

public record CreateDesarrolladorCommand(String country, int cellphone, String email, int projectQuantity, String specialities) {
}
