package com.acme.fromzeroapi.support.interfaces.rest.transform;

import com.acme.fromzeroapi.support.domain.model.commands.CreateSupportTicketCommand;
import com.acme.fromzeroapi.support.interfaces.rest.resources.CreateSupportTicketResource;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

public class CreateSupportTicketCommandFromResourceAsembler {

    public static CreateSupportTicketCommand toCommandFromResource(CreateSupportTicketResource resource, User sender){
        return new CreateSupportTicketCommand(resource.title(), resource.type(), resource.description(), sender);
    }
}
