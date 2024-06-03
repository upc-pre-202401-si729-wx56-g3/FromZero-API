package com.acme.fromzeroapi.project_branch_deliverables.interfaces.acl;

import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import com.acme.fromzeroapi.project_branch_deliverables.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.project_branch_deliverables.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;
    public ProjectContextFacade(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
    }

    public Project getProject(Long projectId) {
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        return project.orElse(null);
    }

}
