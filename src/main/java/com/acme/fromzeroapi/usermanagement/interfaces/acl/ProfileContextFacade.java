package com.acme.fromzeroapi.usermanagement.interfaces.acl;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.usermanagement.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.usermanagement.domain.model.queries.GetDeveloperByUserIdAsyncQuery;
import com.acme.fromzeroapi.usermanagement.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;
import com.acme.fromzeroapi.usermanagement.domain.services.ProfileCommandService;
import com.acme.fromzeroapi.usermanagement.domain.services.ProfileQueryService;
//import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;
import org.springframework.stereotype.Service;

@Service
public class ProfileContextFacade {
    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileContextFacade(ProfileQueryService profileQueryService,
                                ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }

    public Developer getDeveloperByUserId(Long id){
        var getDeveloperByUserIdQuery = new GetDeveloperByUserIdAsyncQuery(id);
        var developer = this.profileQueryService.handle(getDeveloperByUserIdQuery);
        return developer.orElse(null);
    }

    public Enterprise getEnterpriseByUserId(Long id){
        var getEnterpriseByUserIdQuery = new GetEnterpriseByUserIdAsyncQuery(id);
        var enterprise = this.profileQueryService.handle(getEnterpriseByUserIdQuery);
        return enterprise.orElse(null);
    }

    public void updateDeveloperCompletedProjects(Long developerId){
        var developer = profileQueryService.handle(new GetDeveloperByIdQuery(developerId));
        if (developer.isEmpty())return;

        var count = developer.get().getCompletedProjects();
        var command = new UpdateDeveloperCompletedProjectsCommand(developer.get(),count+1);
        var updatedDeveloper = this.profileCommandService.handle(command);

    }
}
