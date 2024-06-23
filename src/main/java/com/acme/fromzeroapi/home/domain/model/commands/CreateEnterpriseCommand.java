package com.acme.fromzeroapi.home.domain.model.commands;

public record CreateEnterpriseCommand(String name,
                                      String description,
                                      String country,
                                      String ruc,
                                      String phone,
                                      String website,
                                      String profileImgUrl,
                                      String sector) {
}