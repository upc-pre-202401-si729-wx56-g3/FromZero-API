package com.acme.fromzeroapi.message.interfaces.rest.resources;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

import java.time.LocalDate;
import java.util.Date;

public record MessageResource(Long id, String subject, String emailBody, User recipient, User sender, LocalDate sentTime) {
}
