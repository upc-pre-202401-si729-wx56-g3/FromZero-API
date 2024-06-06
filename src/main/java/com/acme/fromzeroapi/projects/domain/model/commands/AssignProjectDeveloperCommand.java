package com.acme.fromzeroapi.projects.domain.model.commands;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;

public record AssignProjectDeveloperCommand(Project project, Developer developer) {
}
