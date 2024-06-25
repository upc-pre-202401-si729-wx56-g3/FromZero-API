package com.acme.fromzeroapi.support.application.internal.commandServices;

import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import com.acme.fromzeroapi.support.domain.model.commands.CreateSupportTicketCommand;
import com.acme.fromzeroapi.support.domain.services.SupportTicketCommandService;
import com.acme.fromzeroapi.support.infrastructure.persistence.jpa.repositories.SupportTicketsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupportTicketCommandServiceImpl implements SupportTicketCommandService {

    private final SupportTicketsRepository supportTicketsRepository;

    public SupportTicketCommandServiceImpl(SupportTicketsRepository supportTicketsRepository) {
        this.supportTicketsRepository = supportTicketsRepository;
    }

    @Override
    public Optional<SupportTicket> handle(CreateSupportTicketCommand command) {
        var supportTicket = new SupportTicket(command);
        this.supportTicketsRepository.save(supportTicket);
        return Optional.of(supportTicket);
    }
}
