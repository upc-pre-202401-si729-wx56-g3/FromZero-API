package com.acme.fromzeroapi.home.interfaces.rest.transform;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.home.interfaces.rest.resources.DeveloperResource;

public class DeveloperSourceFromEntityAssembler {
    public static DeveloperResource toResourceFromSource(Developer entity){
        return new DeveloperResource(entity.getId(),
                                     entity.getFirstName(),
                                     entity.getLastName(),
                                     entity.getDescription(),
                                     entity.getCountry(),
                                     entity.getPhone(),
                                     entity.getCompletedProjects(),
                                     entity.getSpecialties(),
                                     entity.getProfileImgUrl(),
                                     entity.getUserId());
    }
}
