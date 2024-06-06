package com.acme.fromzeroapi.developer_branch_projects.interfaces.acl;

import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.developer_branch_projects.domain.services.DeveloperQueryService;
import org.springframework.stereotype.Service;

@Service
public class DeveloperContextFacade {
    private final DeveloperQueryService developerQueryService;
    public DeveloperContextFacade(DeveloperQueryService developerQueryService) {
        this.developerQueryService = developerQueryService;
    }
    public Developer getDeveloperById(Long id){
        var getDeveloperByIdQuery = new GetDeveloperByIdQuery(id);
        var developer = this.developerQueryService.handle(getDeveloperByIdQuery);
        return developer.orElse(null);
    }
}
