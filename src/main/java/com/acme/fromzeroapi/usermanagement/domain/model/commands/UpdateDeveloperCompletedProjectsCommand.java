package com.acme.fromzeroapi.usermanagement.domain.model.commands;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.Developer;

public record UpdateDeveloperCompletedProjectsCommand(Developer developer, int addProject) {
}
