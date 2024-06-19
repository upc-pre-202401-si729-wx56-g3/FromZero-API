package com.acme.fromzeroapi.message.interfaces.rest.transform;

import com.acme.fromzeroapi.message.domain.model.aggregates.Message;
import com.acme.fromzeroapi.message.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {

    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(entity.getId(), entity.getSubject(), entity.getEmailBody(), entity.getRecipient(), entity.getSender(), entity.getCreationTime(), entity.getSentTime());
    }
}
