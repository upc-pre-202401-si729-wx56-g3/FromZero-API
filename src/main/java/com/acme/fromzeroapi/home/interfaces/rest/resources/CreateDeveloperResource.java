package com.acme.fromzeroapi.home.interfaces.rest.resources;

public record CreateDeveloperResource(String firstName,
                                      String LastName,
                                      String description,
                                      String country,
                                      String phone,
                                      int completedProjects,
                                      String specialties,
                                      String profileImgUrl,
                                      Long userId) {
}
