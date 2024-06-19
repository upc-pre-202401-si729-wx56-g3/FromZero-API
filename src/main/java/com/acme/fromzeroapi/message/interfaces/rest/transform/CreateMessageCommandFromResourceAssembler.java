package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.commands.CreateMessageCommand;
import com.acme.fromzeroapi.message.interfaces.rest.resources.CreateMessageResource;

public class CreateMessageCommandFromResourceAssembler {

    public static CreateMessageCommand toCommandFromResource(CreateMessageResource resource){
        return new CreateMessageCommand(resource.subject(), resource.emailBody(), resource.recipient(), resource.sender(), resource.creationTime(), resource.sentTime());
    }

}
