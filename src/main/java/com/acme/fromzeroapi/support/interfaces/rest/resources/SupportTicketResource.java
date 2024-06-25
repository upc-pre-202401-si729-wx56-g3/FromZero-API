package com.acme.fromzeroapi.support.interfaces.rest.resources;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

import java.time.LocalDate;

public record SupportTicketResource(Long id, String title, String type, String description, User sender, LocalDate creationDate){
}
