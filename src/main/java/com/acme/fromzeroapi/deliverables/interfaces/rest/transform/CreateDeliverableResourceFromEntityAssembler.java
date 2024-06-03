package com.acme.fromzeroapi.deliverables.interfaces.rest.transform;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.CreateDeliverableResource;

public class CreateDeliverableResourceFromEntityAssembler {
    public static CreateDeliverableResource toResourceFromEntity(Deliverable entity) {
        return new CreateDeliverableResource(entity.getName(), entity.getDescription(), entity.getDate(), entity.getProject().getId());
    }
}
