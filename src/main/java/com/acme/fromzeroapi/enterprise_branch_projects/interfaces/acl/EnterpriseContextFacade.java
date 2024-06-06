package com.acme.fromzeroapi.enterprise_branch_projects.interfaces.acl;

//import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.queries.GetEnterpriseByIdQuery;
import com.acme.fromzeroapi.enterprise_branch_projects.domain.services.EnterpriseQueryService;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseContextFacade {
    private final EnterpriseQueryService enterpriseQueryService;
    public EnterpriseContextFacade(EnterpriseQueryService enterpriseQueryService) {
        this.enterpriseQueryService = enterpriseQueryService;
    }

    public Enterprise getEnterpriseById(Long id) {
        var getEnterpriseByIdQuery = new GetEnterpriseByIdQuery(id);
        var enterprise = this.enterpriseQueryService.handle(getEnterpriseByIdQuery);
        return enterprise.orElse(null);
    }
}
