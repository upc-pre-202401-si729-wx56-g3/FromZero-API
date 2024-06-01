package com.acme.fromzeroapi.projects.domain.model.queries;

public record GetAllProjectsByStateQuery(String state) {
    public GetAllProjectsByStateQuery {
        if (state == null || state.isEmpty()) {
            throw new IllegalArgumentException("state cannot be null or empty");
        }
    }
}
