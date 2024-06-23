package com.acme.fromzeroapi.home.interfaces.rest.transform;

import com.acme.fromzeroapi.home.domain.model.commands.CreateEnterpriseCommand;
import com.acme.fromzeroapi.home.interfaces.rest.resources.CreateEnterpriseResource;

public class CreateEnterpriseCommandFromResourceAssembler {
    public static CreateEnterpriseCommand toCommandFromSource(CreateEnterpriseResource resource){
         return new CreateEnterpriseCommand(resource.name(),
                                           resource.description(),
                                           resource.country(),
                                           resource.ruc(),
                                           resource.phone(),
                                           resource.website(),
                                           resource.profileImgUrl(),
                                           resource.sector());
    }
}