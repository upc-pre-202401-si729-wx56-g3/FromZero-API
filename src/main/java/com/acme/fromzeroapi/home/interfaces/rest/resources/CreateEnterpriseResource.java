package com.acme.fromzeroapi.home.interfaces.rest.resources;

public record CreateEnterpriseResource(String name, String description, String country, String ruc, String phone, String website, String profileImgUrl, String sector){
}