package com.acme.fromzeroapi.deliverables.domain.model.queries;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;

import java.util.List;

public record GetCompletedDeliverablesQuery(List<Deliverable> deliverables) {
}
