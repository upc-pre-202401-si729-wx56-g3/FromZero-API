package com.acme.fromzeroapi.projects.domain.model.commands;

import com.acme.fromzeroapi.enterprise.domain.model.aggregates.Enterprise;

public record CreateProjectCommand(
        String name, String description, Enterprise enterprise) {

}
