package com.acme.fromzeroapi.projects.interfaces.rest.resources;

public record CreateProjectResource(
        String name, String description,Long ownerId){

}
