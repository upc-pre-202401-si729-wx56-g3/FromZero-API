package com.acme.fromzeroapi.support.domain.model.aggregates;

import com.acme.fromzeroapi.support.domain.model.commands.CreateSupportTicketCommand;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class SupportTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String Type;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false)
    private LocalDate creationDate;

    public SupportTicket(CreateSupportTicketCommand createSupportTicketCommand) {
        this.title = createSupportTicketCommand.title();
        this.Type = createSupportTicketCommand.type();
        this.description = createSupportTicketCommand.description();
        this.sender = createSupportTicketCommand.sender();
        this.creationDate = LocalDate.now();
    }
}
