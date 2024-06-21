package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.commands.CreateMessageCommand;
import com.acme.fromzeroapi.message.interfaces.rest.resources.CreateMessageResource;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

public class CreateMessageCommandFromResourceAssembler {

    public static CreateMessageCommand toCommandFromResource(CreateMessageResource resource, User recipient, User sender){
        return new CreateMessageCommand(resource.subject(), resource.emailBody(), recipient, sender);
    }

}
