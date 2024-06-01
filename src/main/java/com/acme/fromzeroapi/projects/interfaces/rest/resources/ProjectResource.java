package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import java.util.List;

public record ProjectResource(
        Long id, String name, String description, String state, Double progress,
        Long developerId, List<?> candidatesList) {
}
