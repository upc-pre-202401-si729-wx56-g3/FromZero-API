package com.acme.fromzeroapi.message.domain.services;

import com.acme.fromzeroapi.message.domain.model.aggregates.Message;
import com.acme.fromzeroapi.message.domain.model.queries.GetMessageByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {

    Optional<Message> handle(GetMessageByIdQuery query);
    //List<Message> handle(GetMessageByIdQuery query);

}
