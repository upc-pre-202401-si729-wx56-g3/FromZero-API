package com.acme.fromzeroapi.support.interfaces.rest.transform;

import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import com.acme.fromzeroapi.support.interfaces.rest.resources.SupportTicketResource;

public class SupportTicketResourceFromEntityAssembler {

    public static SupportTicketResource toResourceFromEntity(SupportTicket entity) {
        return new SupportTicketResource(entity.getId(), entity.getTitle(), entity.getType(), entity.getDescription(), entity.getSender(), entity.getCreationDate());
    }
}
