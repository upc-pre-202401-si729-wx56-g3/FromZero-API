package com.acme.fromzeroapi.support.application.internal.queryServices;

import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import com.acme.fromzeroapi.support.domain.model.query.GetAllSupportTicket;
import com.acme.fromzeroapi.support.domain.model.query.GetSupportTicketByIdQuery;
import com.acme.fromzeroapi.support.domain.services.SupportTicketQueryService;
import com.acme.fromzeroapi.support.infrastructure.persistence.jpa.repositories.SupportTicketsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportTicketQueryServiceImpl implements SupportTicketQueryService {
    private final SupportTicketsRepository supportTicketsRepository;

    public SupportTicketQueryServiceImpl(SupportTicketsRepository supportTicketsRepository) {
        this.supportTicketsRepository = supportTicketsRepository;
    }

    @Override
    public Optional<SupportTicket> handle(GetSupportTicketByIdQuery query) {
        return this.supportTicketsRepository.findById(query.id());
    }

    @Override
    public List<SupportTicket> handle(GetAllSupportTicket query) {
        return this.supportTicketsRepository.findAll();
    }
}
