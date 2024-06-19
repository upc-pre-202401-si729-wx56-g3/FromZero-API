package com.acme.fromzeroapi.message.domain.services;

import com.acme.fromzeroapi.message.domain.model.aggregates.Message;
import com.acme.fromzeroapi.message.domain.model.commands.CreateMessageCommand;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(CreateMessageCommand command);
}
