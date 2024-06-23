package com.acme.fromzeroapi.home.interfaces.rest.transform;

import com.acme.fromzeroapi.home.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.home.interfaces.rest.resources.EnterpriseResource;

public class EnterpriseSourceFromEntityAssembler {
    public static EnterpriseResource toSourceFromEntity(Enterprise entity){
         return new EnterpriseResource(entity.getId(),
                                       entity.getName(),
                                       entity.getDescription(),
                                       entity.getCountry(),
                                       entity.getRuc(),
                                       entity.getPhone(),
                                       entity.getWebsite(),
                                       entity.getProfileImgUrl(),
                                       entity.getSector());
    }

}