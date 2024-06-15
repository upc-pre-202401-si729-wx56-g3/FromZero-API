package com.acme.fromzeroapi.deliverables.interfaces.rest.resourses;

import java.time.LocalDate;
import java.util.Date;

public record CreateDeliverableResource(
        String name, String description, LocalDate date, Long projectId) {
}
