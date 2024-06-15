package com.acme.fromzeroapi.project_branch_deliverables.interfaces.rest;

import com.acme.fromzeroapi.deliverables.interfaces.acl.DeliverableContextFacade;
import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import com.acme.fromzeroapi.project_branch_deliverables.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/projects",produces = APPLICATION_JSON_VALUE)
public class ProjectController_branch_delvs {
    private final ProjectRepository projectRepository;
    private final DeliverableContextFacade deliverableContextFacade;

    public ProjectController_branch_delvs(ProjectRepository projectRepository,
                                          DeliverableContextFacade deliverableContextFacade) {
        this.projectRepository = projectRepository;
        this.deliverableContextFacade = deliverableContextFacade;
    }

    @PostMapping
    public ResponseEntity<?> createProject(){
        var project = new Project("proyecto 1");
        projectRepository.save(project);
        String methodologies = """
                1. Deliverable1: descripcion1
                """;
        deliverableContextFacade.createDeliverables(methodologies,project);
        return ResponseEntity.ok(project);
    }
}
