package com.acme.fromzeroapi.support.interfaces.rest.resources;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

public record CreateSupportTicketResource(String title, String type, String description, Long senderId) {
}
