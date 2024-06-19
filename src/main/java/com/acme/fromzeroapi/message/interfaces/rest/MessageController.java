package com.acme.fromzeroapi.message.interfaces.rest;

import com.acme.fromzeroapi.message.domain.services.MessageCommandService;
import com.acme.fromzeroapi.message.interfaces.rest.resources.CreateMessageResource;
import com.acme.fromzeroapi.message.interfaces.rest.resources.MessageResource;
import com.acme.fromzeroapi.message.interfaces.rest.transform.CreateMessageCommandFromResourceAssembler;
import com.acme.fromzeroapi.message.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/v1/api/message")
public class MessageController {

    private final MessageCommandService messageCommandService;

    public MessageController(MessageCommandService messageCommandService) {
        this.messageCommandService = messageCommandService;
    }

    @PostMapping
    public ResponseEntity<MessageResource>createMessage(@RequestBody CreateMessageResource resource) {

        var createMessageCommand = CreateMessageCommandFromResourceAssembler.toCommandFromResource(resource);
        var message = this.messageCommandService.handle(createMessageCommand);
        if(message.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(message.get());
        return new ResponseEntity<>(messageResource, HttpStatus.CREATED);
    }

}
