package com.acme.fromzeroapi.deliverables.interfaces.rest;


//import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.UpdateDeliverableStatusCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.UpdateDeveloperMessageCommand;
import com.acme.fromzeroapi.deliverables.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.acme.fromzeroapi.deliverables.domain.model.queries.GetCompletedDeliverablesQuery;
import com.acme.fromzeroapi.deliverables.domain.model.queries.GetDeliverableByIdQuery;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableCommandService;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableQueryService;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.CreateDeliverableResource;
import com.acme.fromzeroapi.deliverables.interfaces.rest.resourses.DeliverableResource;
import com.acme.fromzeroapi.deliverables.interfaces.rest.transform.CreateDeliverableCommandFromResourceAssembler;
//import com.acme.fromzeroapi.deliverables.interfaces.rest.transform.CreateDeliverableResourceFromEntityAssembler;
import com.acme.fromzeroapi.deliverables.interfaces.rest.transform.DeliverableResourceFromEntityAssembler;
//import com.acme.fromzeroapi.project_branch_deliverables.interfaces.acl.ProjectContextFacade;
import com.acme.fromzeroapi.projects.interfaces.acl.ProjectContextFacade;
import com.acme.fromzeroapi.usermanagement.interfaces.acl.ProfileContextFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/deliverables", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Deliverables", description = "Deliverables Management Endpoints")
public class DeliverableController {
    private final DeliverableCommandService deliverableCommandService;
    private final ProjectContextFacade projectContextFacade;
    private final DeliverableQueryService deliverableQueryService;
    private final ProfileContextFacade profileContextFacade;

    public DeliverableController(DeliverableCommandService deliverableCommandService,
                                 ProjectContextFacade projectContextFacade,
                                 DeliverableQueryService deliverableQueryService,
                                 ProfileContextFacade profileContextFacade) {
        this.deliverableCommandService = deliverableCommandService;
        this.projectContextFacade = projectContextFacade;
        this.deliverableQueryService = deliverableQueryService;
        this.profileContextFacade = profileContextFacade;
    }

    @Operation(summary = "Create deliverable")
    @PostMapping
    public ResponseEntity<DeliverableResource> createDeliverable(@RequestBody CreateDeliverableResource resource) {
        var project = this.projectContextFacade.getProjectById(resource.projectId());
        if (project == null) {
            return ResponseEntity.badRequest().build();
        }
        var createDeliverableCommand = CreateDeliverableCommandFromResourceAssembler.toCommandFromResource(resource,project);
        var deliverable = this.deliverableCommandService.handle(createDeliverableCommand);
        if (deliverable.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        var getAllDeliverablesByProjectId = new GetAllDeliverablesByProjectIdQuery(deliverable.get().getProject());
        var deliverables = this.deliverableQueryService.handle(getAllDeliverablesByProjectId);
        var getCompletedDeliverablesQuery = new GetCompletedDeliverablesQuery(deliverables);
        var completedDeliverables = deliverableQueryService.handle(getCompletedDeliverablesQuery);
        var totalDeliverables = deliverables.size();
        this.projectContextFacade.updateProjectProgress(deliverable.get().getProject().getId()
                , completedDeliverables, totalDeliverables);

        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return new  ResponseEntity<>(deliverableResource, HttpStatus.CREATED);
    }

    @GetMapping(value = "/project/{projectId}")
    @Operation(summary = "Get All Deliverables By Project Id")
    public ResponseEntity<List<DeliverableResource>> getAllDeliverablesByProjectId(@PathVariable Long projectId){
        var project = this.projectContextFacade.getProjectById(projectId);
        if (project==null)return ResponseEntity.badRequest().build();
        var getAllDeliverablesByProjectQuery = new GetAllDeliverablesByProjectIdQuery(project);
        var deliverables = this.deliverableQueryService.handle(getAllDeliverablesByProjectQuery);
        var deliverablesResources = deliverables.stream()
                .map(DeliverableResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deliverablesResources);
    }

    @GetMapping(value = "/{deliverableId}")
    @Operation(summary = "Get Deliverable By Id")
    public ResponseEntity<DeliverableResource> getDeliverableById(@PathVariable Long deliverableId){
        var getDeliverableByIdQuery = new GetDeliverableByIdQuery(deliverableId);
        var deliverable = this.deliverableQueryService.handle(getDeliverableByIdQuery);
        if (deliverable.isEmpty())return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Send Deliverable")
    @PatchMapping(value = "/{deliverableId}/send")
    public ResponseEntity<DeliverableResource> sendDeliverable(@PathVariable Long deliverableId,
                                             @RequestBody String developerMessage){
        var updateDeveloperMessageCommand = new UpdateDeveloperMessageCommand(deliverableId,developerMessage);
        var deliverable = this.deliverableCommandService.handle(updateDeveloperMessageCommand);
        if (deliverable.isEmpty())return ResponseEntity.badRequest().build();
        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }

    @Operation(summary = "Review Deliverable")
    @PatchMapping(value = "/{deliverableId}/review")
    public ResponseEntity<DeliverableResource> reviewDeliverable(@PathVariable Long deliverableId,
                                                                 @RequestBody Boolean accepted){
        var updateDeliverableStatusCommand = new UpdateDeliverableStatusCommand(deliverableId,accepted);
        var deliverable = this.deliverableCommandService.handle(updateDeliverableStatusCommand);
        if (deliverable.isEmpty())return ResponseEntity.badRequest().build();

        if("Completed".equals(deliverable.get().getState())) {
            var getAllDeliverablesByProjectId = new GetAllDeliverablesByProjectIdQuery(deliverable.get().getProject());
            var deliverables = this.deliverableQueryService.handle(getAllDeliverablesByProjectId);
            var getCompletedDeliverablesQuery = new GetCompletedDeliverablesQuery(deliverables);
            var completedDeliverables = deliverableQueryService.handle(getCompletedDeliverablesQuery);
            var totalDeliverables = deliverables.size();
            var updatedProject = this.projectContextFacade.updateProjectProgress(deliverable.get().getProject().getId()
                    , completedDeliverables, totalDeliverables);
            if(updatedProject==null)return ResponseEntity.internalServerError().build();
            if(updatedProject.getProgress()==100.0){
                this.profileContextFacade.updateDeveloperCompletedProjects(updatedProject.getDeveloper().getId());
            }
        }

        var deliverableResource = DeliverableResourceFromEntityAssembler.toResourceFromEntity(deliverable.get());
        return ResponseEntity.ok(deliverableResource);
    }
}
