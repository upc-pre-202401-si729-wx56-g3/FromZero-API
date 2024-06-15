package com.acme.fromzeroapi.deliverables.interfaces.acl;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableCommandService;
import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import org.springframework.stereotype.Service;

@Service
public class DeliverableContextFacade {

    private final DeliverableCommandService deliverableCommandService;

    public DeliverableContextFacade(DeliverableCommandService deliverableCommandService) {
        this.deliverableCommandService = deliverableCommandService;
    }

    public void createDeliverables(String methodologies, Project project){
        if(methodologies.isEmpty()){

        }
        var createDeliverableCommand = new CreateDeliverableCommand();
    }
}
