package com.acme.fromzeroapi.projects.domain.model.queries;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;

public record GetAllProjectsByDeveloperIdQuery(Developer developer) {
}
