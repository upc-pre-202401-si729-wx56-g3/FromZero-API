package com.acme.fromzeroapi.home.domain.model.commands;

public record CreateDeveloperCommand(String firstName,
         String LastName,
         String description,
         String country,
         String phone,
         int completedProjects,
         String specialties,
         String profileImgUrl,
         Long userId) {
}
