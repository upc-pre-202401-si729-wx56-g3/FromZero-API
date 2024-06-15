package com.acme.fromzeroapi.deliverables.interfaces.rest.resourses;

//import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;

import java.time.LocalDate;

public record DeliverableResource(
        Long id, String name, String description, LocalDate date, String state, Project project) {
}
