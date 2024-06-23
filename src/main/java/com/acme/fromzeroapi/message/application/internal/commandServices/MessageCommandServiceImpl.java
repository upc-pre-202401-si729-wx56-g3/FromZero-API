package com.acme.fromzeroapi.message.application.internal.commandServices;

import com.acme.fromzeroapi.message.domain.model.aggregates.Message;
import com.acme.fromzeroapi.message.domain.model.commands.CreateMessageCommand;
import com.acme.fromzeroapi.message.domain.services.MessageCommandService;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        var message = new Message(command);
        this.messageRepository.save(message);
        return Optional.of(message);
    }
}
