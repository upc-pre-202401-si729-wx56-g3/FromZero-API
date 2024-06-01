package com.acme.fromzeroapi.projects.interfaces.rest;

import com.acme.fromzeroapi.enterprise.interfaces.acl.EnterpriseContextFacade;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.CreateProjectResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/projects", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Projects", description = "Projects Management Endpoints")
public class ProjectController {
    private final ProjectCommandService projectCommandService;
    private final EnterpriseContextFacade enterpriseContextFacade;

    public ProjectController(ProjectCommandService projectCommandService,
                             EnterpriseContextFacade enterpriseContextFacade) {
        this.projectCommandService = projectCommandService;
        this.enterpriseContextFacade = enterpriseContextFacade;
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

}
