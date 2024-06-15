package com.acme.fromzeroapi.deliverables.interfaces.rest;


//import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableCommandService;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.CreateDeliverableResource;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.DeliverableResource;
import com.acme.fromzeroapi.deliverables.interfaces.rest.transform.CreateDeliverableCommandFromResourceAssembler;
//import com.acme.fromzeroapi.deliverables.interfaces.rest.transform.CreateDeliverableResourceFromEntityAssembler;
import com.acme.fromzeroapi.deliverables.interfaces.rest.transform.DeliverableResourceFromEntityAssembler;
import com.acme.fromzeroapi.project_branch_deliverables.interfaces.acl.ProjectContextFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/deliverables", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Deliverables", description = "Deliverables Management Endpoints")
public class DeliverableController {
    private final DeliverableCommandService deliverableCommandService;
    private final ProjectContextFacade projectContextFacade;


    public DeliverableController(DeliverableCommandService deliverableCommandService,
                                 ProjectContextFacade projectContextFacade) {
        this.deliverableCommandService = deliverableCommandService;
        this.projectContextFacade = projectContextFacade;
    }

    @Operation(summary = "Create deliverable")
    @PostMapping
    public ResponseEntity<DeliverableResource> createDeliverable(@RequestBody CreateDeliverableResource resource) {
        var project = this.projectContextFacade.getProject(resource.projectId());
        if (project == null) {
            return ResponseEntity.badRequest().build();
        }
        var createDeliverableCommand = CreateDeliverableCommandFromResourceAssembler.toCommandFromResource(resource,project);
        //var createDeliverableCommand = new CreateDeliverableCommand(resource.name(), resource.description(), resource.date(), project);
        var deliverable = this.deliverableCommandService.handle(createDeliverableCommand);
        if (deliverable.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        //var createdDeliverableResource = CreateDeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return new  ResponseEntity<>(deliverableResource, HttpStatus.CREATED);
    }

}
