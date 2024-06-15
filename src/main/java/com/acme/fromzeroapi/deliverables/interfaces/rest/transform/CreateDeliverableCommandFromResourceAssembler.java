package com.acme.fromzeroapi.deliverables.interfaces.rest.transform;

import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.CreateDeliverableResource;
import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;

public class CreateDeliverableCommandFromResourceAssembler {
    public static CreateDeliverableCommand toCommandFromResource(CreateDeliverableResource resource, Project project){
        return new CreateDeliverableCommand(resource.name(),resource.description(),resource.date(),project);
    }
}
