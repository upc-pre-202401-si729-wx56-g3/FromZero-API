package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.AssignProjectDeveloperResource;

public class AssignedProjectDeveloperResourceFromEntityAssembler {
    public static AssignProjectDeveloperResource toResourceFromEntity(Project entity){
        return new AssignProjectDeveloperResource(entity.getName(),entity.getDescription(),
                entity.getState(),entity.getDeveloper(),entity.getCandidates());
    }
}
