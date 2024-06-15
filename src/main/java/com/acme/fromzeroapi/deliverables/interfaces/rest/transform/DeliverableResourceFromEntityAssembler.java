package com.acme.fromzeroapi.deliverables.interfaces.rest.transform;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.DeliverableResource;

public class DeliverableResourceFromEntityAssembler {
    public static DeliverableResource toResourceFromEntity(Deliverable entity){
        return new DeliverableResource(
                entity.getId(),entity.getName(),entity.getDescription(),
                entity.getDate(),entity.getState(),entity.getProject());
    }
}
