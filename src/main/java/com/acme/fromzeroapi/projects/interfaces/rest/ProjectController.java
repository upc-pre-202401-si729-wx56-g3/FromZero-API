package com.acme.fromzeroapi.projects.interfaces.rest;

import com.acme.fromzeroapi.enterprise.interfaces.acl.EnterpriseContextFacade;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsByStateQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.ProjectResource;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.CreateProjectResourceFromEntityAssembler;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/projects", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Projects", description = "Projects Management Endpoints")
public class ProjectController {
    private final ProjectCommandService projectCommandService;
    private final EnterpriseContextFacade enterpriseContextFacade;
    private final ProjectQueryService projectQueryService;

    public ProjectController(ProjectCommandService projectCommandService,
                             EnterpriseContextFacade enterpriseContextFacade,
                             ProjectQueryService projectQueryService) {
        this.projectCommandService = projectCommandService;
        this.enterpriseContextFacade = enterpriseContextFacade;
        this.projectQueryService = projectQueryService;
    }

    @Operation(summary = "Create project")
    @PostMapping
    public ResponseEntity<CreateProjectResource> createProject(@RequestBody CreateProjectResource resource){
        var enterprise = this.enterpriseContextFacade.getEnterpriseById(resource.ownerId());
        if(enterprise==null)return  ResponseEntity.badRequest().build();
        var createProjectCommand = new CreateProjectCommand(resource.name(),resource.description(),enterprise);
        var project = this.projectCommandService.handle(createProjectCommand);
        if(project.isEmpty())return  ResponseEntity.badRequest().build();
        var createProjectResource = CreateProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return new ResponseEntity<>(createProjectResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Projects")
    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects(){
        var getAllProjectsQuery = new GetAllProjectsQuery();
        var projects = this.projectQueryService.handle(getAllProjectsQuery);
        var projectsResources=projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }

    @Operation(summary = "Get All Project By State")
    @GetMapping(value = "/by-state")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByState(@RequestParam String state){
        try {
            var getAllProjectsByStateQuery = new GetAllProjectsByStateQuery(state);
            var projects = this.projectQueryService.handle(getAllProjectsByStateQuery);
            var projectsResources = projects.stream()
                    .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(projectsResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }
}
