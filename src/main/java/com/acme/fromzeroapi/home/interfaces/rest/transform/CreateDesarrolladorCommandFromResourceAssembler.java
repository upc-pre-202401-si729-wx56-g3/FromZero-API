package com.acme.fromzeroapi.home.interfaces.rest.transform;

import com.acme.fromzeroapi.home.domain.model.commands.CreateDesarrolladorCommand;
import com.acme.fromzeroapi.home.interfaces.rest.resources.CreateDesarrolladorResource;

public class CreateDesarrolladorCommandFromResourceAssembler {
    public static CreateDesarrolladorCommand toCommandFromSource(CreateDesarrolladorResource resource){
         return new CreateDesarrolladorCommand(resource.country(), resource.cellphone(), resource.email(), resource.projectQuantity(), resource.specialities());
    }
}
