package com.acme.fromzeroapi.support.interfaces.rest;

import com.acme.fromzeroapi.projects.domain.model.queries.GetAllProjectsQuery;
import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import com.acme.fromzeroapi.support.domain.model.query.GetAllSupportTicketQuery;
import com.acme.fromzeroapi.support.domain.services.SupportTicketCommandService;
import com.acme.fromzeroapi.support.domain.services.SupportTicketQueryService;
import com.acme.fromzeroapi.support.interfaces.rest.resources.CreateSupportTicketResource;
import com.acme.fromzeroapi.support.interfaces.rest.resources.SupportTicketResource;
import com.acme.fromzeroapi.support.interfaces.rest.transform.CreateSupportTicketCommandFromResourceAsembler;
import com.acme.fromzeroapi.support.interfaces.rest.transform.SupportTicketResourceFromEntityAssembler;
import com.acme.fromzeroapi.usermanagement.interfaces.acl.IamContextFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="v1/api/support-tickets")
@Tag(name = "Support Tickets", description = "Support Tickets management endpoint")
public class SupportTicketController {

    private final SupportTicketCommandService supportTicketCommandService;
    private final SupportTicketQueryService supportTicketQueryService;
    private final IamContextFacade iamContextFacade;

    public SupportTicketController(SupportTicketCommandService supportTicketCommandService, SupportTicketQueryService supportTicketQueryService, IamContextFacade iamContextFacade) {
        this.supportTicketCommandService = supportTicketCommandService;
        this.supportTicketQueryService = supportTicketQueryService;
        this.iamContextFacade = iamContextFacade;
    }

    @Operation(summary = "Create Support Ticket")
    @PostMapping
    public ResponseEntity<SupportTicketResource>createSupportTicket(@RequestBody CreateSupportTicketResource resource){
        var recipientUser = this.iamContextFacade.getUserById(resource.senderId());
        if(recipientUser == null){
            return ResponseEntity.badRequest().build();
        }
        var createSupportTicketCommand = CreateSupportTicketCommandFromResourceAsembler.toCommandFromResource(resource, recipientUser);
        var supportTicket = this.supportTicketCommandService.handle(createSupportTicketCommand);
        if(supportTicket.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var supportTicketResource = SupportTicketResourceFromEntityAssembler.toResourceFromEntity(supportTicket.get());
        return new ResponseEntity<>(supportTicketResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all support tickets")
    @GetMapping
    public ResponseEntity<List<SupportTicketResource>> getAllSupportTickets(){
        var getAllSupportTicketsQuery = new GetAllSupportTicketQuery();
        var supportTickets = this.supportTicketQueryService.handle(getAllSupportTicketsQuery);
        var supportTicketsResource = supportTickets.stream()
                .map(SupportTicketResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(supportTicketsResource);
    }
}
