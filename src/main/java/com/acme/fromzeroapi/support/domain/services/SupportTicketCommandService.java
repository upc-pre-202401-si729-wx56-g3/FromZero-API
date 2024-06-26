package com.acme.fromzeroapi.support.domain.services;

import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import com.acme.fromzeroapi.support.domain.model.commands.CreateSupportTicketCommand;

import java.util.Optional;

public interface SupportTicketCommandService {
    Optional<SupportTicket> handle(CreateSupportTicketCommand command);
}
