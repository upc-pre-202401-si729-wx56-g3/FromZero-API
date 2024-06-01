package com.acme.fromzeroapi.projects.interfaces.acl;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;

    public ProjectContextFacade(ProjectQueryService projectQueryService) {
        this.projectQueryService = projectQueryService;
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
}
