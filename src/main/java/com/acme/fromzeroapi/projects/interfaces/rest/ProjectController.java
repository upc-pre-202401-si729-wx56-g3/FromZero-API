package com.acme.fromzeroapi.projects.interfaces.rest;

import com.acme.fromzeroapi.developer_branch_projects.interfaces.acl.DeveloperContextFacade;
import com.acme.fromzeroapi.enterprise_branch_projects.interfaces.acl.EnterpriseContextFacade;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.domain.model.commands.AssignProjectDeveloperCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.acme.fromzeroapi.projects.domain.model.queries.*;
import com.acme.fromzeroapi.projects.domain.services.FrameworksQueryService;
import com.acme.fromzeroapi.projects.domain.services.ProgrammingLanguagesQueryService;
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
import java.util.Optional;
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
    private final ProgrammingLanguagesQueryService programmingLanguagesQueryService;
    private final FrameworksQueryService frameworksQueryService;

    public ProjectController(ProjectCommandService projectCommandService,
                             EnterpriseContextFacade enterpriseContextFacade,
                             ProjectQueryService projectQueryService,
                             DeveloperContextFacade developerContextFacade,
                             ProgrammingLanguagesQueryService programmingLanguagesQueryService,
                             FrameworksQueryService frameworksQueryService) {
        this.projectCommandService = projectCommandService;
        this.enterpriseContextFacade = enterpriseContextFacade;
        this.projectQueryService = projectQueryService;
        this.developerContextFacade = developerContextFacade;
        this.programmingLanguagesQueryService = programmingLanguagesQueryService;
        this.frameworksQueryService = frameworksQueryService;
    }

    public List<ProgrammingLanguage> getProgrammingLanguages(List<Integer>languages) {
        List<Optional<ProgrammingLanguage>> optionalProgrammingLanguages = languages.stream()
                .map(item -> {
                    var getLanguageById = new GetProgrammingLanguageByIdQuery(item);
                    return this.programmingLanguagesQueryService.handle(getLanguageById);
                })
                .collect(Collectors.toList());

        // Verificar si alguno de los Optional está vacío
        /*if (optionalProgrammingLanguages.stream().anyMatch(Optional::isEmpty)) {
            return ResponseEntity.badRequest().build();
        }*/

        // Convertir los Optional a ProgrammingLanguage
        List<ProgrammingLanguage> programmingLanguages = optionalProgrammingLanguages.stream()
                .map(Optional::get)
                .collect(Collectors.toList());
        return programmingLanguages;
    }

    public List<Framework> getFrameworks(List<Integer> frameworks){
        List<Optional<Framework>> optionalFrameworks = frameworks.stream()
                .map(item -> {
                    var getFrameworkById = new GetFrameworkByIdQuery(item);
                    return this.frameworksQueryService.handle(getFrameworkById);
                })
                .collect(Collectors.toList());

        List<Framework> frameworksList=optionalFrameworks.stream()
                .map(Optional::get)
                .collect(Collectors.toList());
        return frameworksList;
    }

    @Operation(summary = "Create project")
    @PostMapping
    public ResponseEntity<CreateProjectResource> createProject(@RequestBody CreateProjectResource resource) {
        var enterprise = this.enterpriseContextFacade.getEnterpriseById(resource.ownerId());
        if (enterprise == null) return ResponseEntity.badRequest().build();
        var programmingLanguages = getProgrammingLanguages(resource.languages());
        var frameworks=getFrameworks(resource.frameworks());
        var createProjectCommand = new CreateProjectCommand(resource.name(), resource.description(), enterprise,
                programmingLanguages,frameworks,resource.type(),resource.budget(),resource.methodologies());
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

    @Operation(summary = "Get All Projects By Developer Id")
    @GetMapping(value = "developer/{developerId}")
    public ResponseEntity<List<ProjectResource>> getAllProjectsByDeveloperId(@PathVariable Long developerId){
        //get developer con el facade
        var developer = this.developerContextFacade.getDeveloperById(developerId);
        if(developer==null) return ResponseEntity.badRequest().build();
        var getProjectsByDeveloperIdQuery = new GetAllProjectsByDeveloperIdQuery(developer);
        var projects=this.projectQueryService.handle(getProjectsByDeveloperIdQuery);
        var projectsResources = projects.stream()
                .map(ProjectResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsResources);
    }
}