package com.acme.fromzeroapi.message.interfaces.rest;

import com.acme.fromzeroapi.message.domain.model.queries.GetAllMessageByRecipientByIdQuery;
import com.acme.fromzeroapi.message.domain.services.MessageCommandService;
import com.acme.fromzeroapi.message.domain.services.MessageQueryService;
import com.acme.fromzeroapi.message.interfaces.rest.resources.CreateMessageResource;
import com.acme.fromzeroapi.message.interfaces.rest.resources.MessageResource;
import com.acme.fromzeroapi.message.interfaces.rest.transform.CreateMessageCommandFromResourceAssembler;
import com.acme.fromzeroapi.message.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import com.acme.fromzeroapi.usermanagement.interfaces.acl.IamContextFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/v1/api/message")
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final IamContextFacade iamContextFacade;
    private final MessageQueryService messageQueryService;

    public MessageController(MessageCommandService messageCommandService, IamContextFacade iamContextFacade, MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.iamContextFacade = iamContextFacade;
        this.messageQueryService = messageQueryService;
    }

    @PostMapping
    public ResponseEntity<MessageResource>createMessage(@RequestBody CreateMessageResource resource) {

        var recipientUser = this.iamContextFacade.getUserById(resource.recipientId());
        if (recipientUser == null) {
            return ResponseEntity.badRequest().build();
        }

        var senderUser = this.iamContextFacade.getUserById(resource.senderId());
        if (senderUser == null) {
            return ResponseEntity.badRequest().build();
        }

        var createMessageCommand = CreateMessageCommandFromResourceAssembler.toCommandFromResource(resource, recipientUser, senderUser);
        var message = this.messageCommandService.handle(createMessageCommand);
        if(message.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(message.get());
        return new ResponseEntity<>(messageResource, HttpStatus.CREATED);
    }

    @GetMapping(value = "/recipient/{recipientId}")
    public ResponseEntity<List<MessageResource>> getMessages(@PathVariable Long recipientId) {
        var user = this.iamContextFacade.getUserById(recipientId);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        var getAllMessageByRecipientIdQuery = new GetAllMessageByRecipientByIdQuery(user);
        var messages = this.messageQueryService.handle(getAllMessageByRecipientIdQuery);
        var messagesResource = messages.stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messagesResource);
    }

}
