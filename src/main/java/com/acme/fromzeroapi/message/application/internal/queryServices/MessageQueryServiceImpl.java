package com.acme.fromzeroapi.message.application.internal.queryServices;

import com.acme.fromzeroapi.message.domain.model.aggregates.Message;
import com.acme.fromzeroapi.message.domain.model.queries.GetAllMessageByRecipientByIdQuery;
import com.acme.fromzeroapi.message.domain.model.queries.GetMessageByIdQuery;
import com.acme.fromzeroapi.message.domain.services.MessageQueryService;
import com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(GetMessageByIdQuery query) {
        return this.messageRepository.findById(query.id());
    }

    @Override
    public List<Message> handle(GetAllMessageByRecipientByIdQuery query) {
        return this.messageRepository.findAllByRecipient(query.recipient());
    }
}
