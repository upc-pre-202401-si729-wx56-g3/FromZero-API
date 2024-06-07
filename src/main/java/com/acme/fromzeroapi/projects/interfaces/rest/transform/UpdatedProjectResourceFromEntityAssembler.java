package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.UpdateProjectCandidatesListResource;

public class UpdatedProjectResourceFromEntityAssembler {
    public static UpdateProjectCandidatesListResource toResourceFromEntity(Project entity){
        return new UpdateProjectCandidatesListResource(entity.getName(),entity.getDescription(),entity.getCandidates());
    }
}
