package com.acme.fromzeroapi.home.interfaces.rest.transform;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import com.acme.fromzeroapi.home.interfaces.rest.resources.DesarrolladorResource;

public class DesarrolladorSourceFromEntityAssembler{
    public static DesarrolladorResource toResourceFromSource(Desarrollador entity){
        return new DesarrolladorResource(entity.getId(), entity.getCountry(), entity.getCellphone(), entity.getEmail(), entity.getProjectQuantity(), entity.getSpecialities());
    }
}
