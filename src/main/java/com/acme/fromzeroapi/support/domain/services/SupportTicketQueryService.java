package com.acme.fromzeroapi.support.domain.services;

import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import com.acme.fromzeroapi.support.domain.model.query.GetAllSupportTicket;
import com.acme.fromzeroapi.support.domain.model.query.GetSupportTicketByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SupportTicketQueryService {
    Optional<SupportTicket> handle(GetSupportTicketByIdQuery query);
    List<SupportTicket> handle(GetAllSupportTicket query);
}
