package com.acme.fromzeroapi.home.interfaces.rest.transform;

import com.acme.fromzeroapi.home.domain.model.commands.CreateDeveloperCommand;
import com.acme.fromzeroapi.home.interfaces.rest.resources.CreateDeveloperResource;

public class CreateDeveloperCommandFromResourceAssembler {
    public static CreateDeveloperCommand toCommandFromSource(CreateDeveloperResource resource){
         return new CreateDeveloperCommand(resource.firstName(),
                                           resource.LastName(),
                                           resource.description(),
                                           resource.country(),
                                           resource.phone(),
                                           resource.completedProjects(),
                                           resource.specialties(),
                                           resource.profileImgUrl(),
                                           resource.userId());
    }
}
