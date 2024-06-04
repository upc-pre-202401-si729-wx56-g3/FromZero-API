package com.acme.fromzeroapi.projects.interfaces.rest;

import com.acme.fromzeroapi.developer_branch_projects.interfaces.acl.DeveloperContextFacade;
import com.acme.fromzeroapi.enterprise_branch_projects.interfaces.acl.EnterpriseContextFacade;
import com.acme.fromzeroapi.projects.domain.model.commands.AssignProjectDeveloperCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsByStateQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.AssignProjectDeveloperResource;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.ProjectResource;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.UpdateProjectCandidatesListResource;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.AssignedProjectDeveloperResourceFromEntityAssembler;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.CreateProjectResourceFromEntityAssembler;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.ProjectResourceFromEntityAssembler;
import com.acme.fromzeroapi.projects.interfaces.rest.transform.UpdatedProjectResourceFromEntityAssembler;
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
    private final DeveloperContextFacade developerContextFacade;

    public ProjectController(ProjectCommandService projectCommandService,
                             EnterpriseContextFacade enterpriseContextFacade,
                             ProjectQueryService projectQueryService,
                             DeveloperContextFacade developerContextFacade) {
        this.projectCommandService = projectCommandService;
        this.enterpriseContextFacade = enterpriseContextFacade;
        this.projectQueryService = projectQueryService;
        this.developerContextFacade = developerContextFacade;
    }

    @Operation(summary = "Create project")
    @PostMapping
    public ResponseEntity<CreateProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        var enterprise = this.enterpriseContextFacade.getEnterpriseById(resource.ownerId());
        if (enterprise == null) return ResponseEntity.badRequest().build();
        var createProjectCommand = new CreateProjectCommand(resource.name(), resource.description(), enterprise);
        var project = this.projectCommandService.handle(createProjectCommand);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var createProjectResource = CreateProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return new ResponseEntity<>(createProjectResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Projects")
    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects() {
        var getAllProjectsQuery = new GetAllProjectsQuery();
        var projects = this.projectQueryService.handle(getAllProjectsQuery);
        var projectsResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }

    @Operation(summary = "Get All Project By State")
    @GetMapping(value = "/by-state")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByState(@RequestParam String state) {
        try {
            var getAllProjectsByStateQuery = new GetAllProjectsByStateQuery(state);
            var projects = this.projectQueryService.handle(getAllProjectsByStateQuery);
            var projectsResources = projects.stream()
                    .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(projectsResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get Project By Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long id) {
        var getProjectByIdQuery = new GetProjectByIdQuery(id);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var projectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
        return ResponseEntity.ok(projectResource);
    }

    @Operation(summary = "Add Candidate to Project")
    @PatchMapping(value = "/{projectId}/add-candidate")
    public ResponseEntity<UpdateProjectCandidatesListResource>
    updateProjectCandidatesList(@PathVariable Long projectId,
                                @RequestBody Long developerId) {
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        //get developer by id, usar developer context facade
        var developer = this.developerContextFacade.getDeveloperById(developerId);
        if (developer == null) return ResponseEntity.badRequest().build();
        var updateProjectCandidatesListCommand = new UpdateProjectCandidatesListCommand(developer, project.get());
        var updatedProject = this.projectCommandService.handle(updateProjectCandidatesListCommand);
        if (updatedProject.isEmpty()) return ResponseEntity.badRequest().build();
        //to resource
        var updatedProjectResource = UpdatedProjectResourceFromEntityAssembler.toResourceFromEntity(updatedProject.get());
        return ResponseEntity.ok(updatedProjectResource);
    }

    @Operation(summary = "Assign Developer to a Project")
    @PatchMapping(value = "/{projectId}/assign-developer")
    public ResponseEntity<AssignProjectDeveloperResource>
    setProjectDeveloper(@PathVariable Long projectId,
                        @RequestBody Long developerId) {
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        if (project.isEmpty()) return ResponseEntity.badRequest().build();
        var developer = this.developerContextFacade.getDeveloperById(developerId);
        if (developer == null) return ResponseEntity.badRequest().build();
        var assignProjectDeveloperCommand= new AssignProjectDeveloperCommand(project.get(),developer);
        var updatedProject = this.projectCommandService.handle(assignProjectDeveloperCommand);
        if (updatedProject.isEmpty()) return ResponseEntity.badRequest().build();
        var updatedProjectResource = AssignedProjectDeveloperResourceFromEntityAssembler
                .toResourceFromEntity(updatedProject.get());
        return ResponseEntity.ok(updatedProjectResource);
    }

}
