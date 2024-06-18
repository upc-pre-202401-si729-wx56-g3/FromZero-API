package com.acme.fromzeroapi.auth.interfaces.acl;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.queries.GetDeveloperByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;
import com.acme.fromzeroapi.auth.domain.services.ProfileQueryService;
import com.acme.fromzeroapi.developer_branch_projects.domain.model.queries.GetDeveloperByIdQuery;

public class ProfileContextFacade {
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacade(ProfileQueryService profileQueryService) {
        this.profileQueryService = profileQueryService;
    }

    public Developer getDeveloperUserById(Long id){
        var getDeveloperByUserIdQuery = new GetDeveloperByUserIdAsyncQuery(id);
        var developer = this.profileQueryService.handle(getDeveloperByUserIdQuery);
        return developer.orElse(null);
    }

    public Enterprise getEnterpruseByUserId(Long id){
        var getEnterpriseByUserIdQuery = new GetEnterpriseByUserIdAsyncQuery(id);
        var enterprise = this.profileQueryService.handle(getEnterpriseByUserIdQuery);
        return enterprise.orElse(null);
    }
}
