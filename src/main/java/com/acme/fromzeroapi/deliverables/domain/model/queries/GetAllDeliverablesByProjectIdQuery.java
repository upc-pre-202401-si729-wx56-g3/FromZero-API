package com.acme.fromzeroapi.deliverables.domain.model.queries;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;

public record GetAllDeliverablesByProjectIdQuery(Project project) {
}
