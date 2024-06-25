package com.acme.fromzeroapi.projects.interfaces.acl;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsByStateQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;
import com.acme.fromzeroapi.projects.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;
    private final ProjectCommandService projectCommandService;

    public ProjectContextFacade(ProjectQueryService projectQueryService,
                                ProjectCommandService projectCommandService) {
        this.projectQueryService = projectQueryService;
        this.projectCommandService = projectCommandService;
    }

    /*
     * Se espera que el contexto que utilize este metodo
     * haga un resource el cual manipule o haga uso de
     * algunos atributos de la entidad Project que se
     * está devolviendo
     * */
    public List<Project> getAllProjects(){
        var getAllProjectsQuery = new GetAllProjectsQuery();
        return this.projectQueryService.handle(getAllProjectsQuery);
    }

    public List<Project> getAllProjectsByState(String state){
        try{
            var getAllProjectsByStateQuery = new GetAllProjectsByStateQuery(state);
            return this.projectQueryService.handle(getAllProjectsByStateQuery);
        }catch (IllegalArgumentException e){
            return Collections.emptyList();
        }
    }

    public Project getProjectById(Long id){
        var getProjectByIdQuery = new GetProjectByIdQuery(id);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        return project.orElse(null);
    }

    public void updateProjectProgress(Long projectId,Long completedDeliverables, Integer totalDeliverables){
        double percentComplete = (double) completedDeliverables / totalDeliverables * 100;
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        try {
            var project = this.projectQueryService.handle(getProjectByIdQuery);
            if(project.isEmpty())throw new IllegalArgumentException();
            var updateProjectProgress = new UpdateProjectProgressCommand(project.get(),percentComplete);
            this.projectCommandService.handle(updateProjectProgress);
        }catch (IllegalArgumentException e){
            return;
        }
    }
}
