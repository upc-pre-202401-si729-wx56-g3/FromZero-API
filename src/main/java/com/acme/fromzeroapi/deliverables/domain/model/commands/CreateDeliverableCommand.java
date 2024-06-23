package com.acme.fromzeroapi.deliverables.domain.model.commands;

//import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;

import java.time.LocalDate;
//import java.util.Date;

public record CreateDeliverableCommand(
        String name, String description, LocalDate date, Project project) {
}
