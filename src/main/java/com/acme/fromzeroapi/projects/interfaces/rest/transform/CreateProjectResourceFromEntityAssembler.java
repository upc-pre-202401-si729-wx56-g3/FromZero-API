package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectResourceFromEntityAssembler {
    public static CreateProjectResource toResourceFromEntity(Project entity) {
        return new CreateProjectResource(entity.getName(), entity.getDescription(), entity.getEnterprise().getId());
    }
}
