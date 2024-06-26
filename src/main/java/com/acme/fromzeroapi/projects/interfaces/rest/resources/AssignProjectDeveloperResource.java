package com.acme.fromzeroapi.projects.interfaces.rest.resources;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;

import java.util.List;

public record AssignProjectDeveloperResource(
        String name, String description, String state,
        Developer developer, List<Developer> candidates) {
}
