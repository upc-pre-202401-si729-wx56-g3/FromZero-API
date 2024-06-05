package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import java.util.List;

public record CreateProjectResource(
        String name, String description,Long ownerId,List<Integer> languages,
        List<Integer> frameworks){

    /*public CreateProjectResource (String name, String description, Long ownerId){
        this(name, description, ownerId, List.of(),List.of());
    }*/
}
