package com.acme.fromzeroapi.projects.domain.model.queries;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Enterprise;

public record GetAllProjectsByEnterpriseIdQuery(Enterprise enterprise) {
}
