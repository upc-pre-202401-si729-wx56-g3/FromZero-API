package com.acme.fromzeroapi.message.domain.model.aggregates;

import com.acme.fromzeroapi.message.domain.model.commands.CreateMessageCommand;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String emailBody;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private Date creationTime;

    @Column(nullable = false)
    private Date sentTime;

    public Message(CreateMessageCommand createMessageCommand) {
        this.subject = createMessageCommand.subject();
        this.emailBody = createMessageCommand.emailBody();
        this.recipient = createMessageCommand.recipient();
        this.sender = createMessageCommand.sender();
        this.creationTime = new Date();
        this.sentTime = new Date();
    }

    public Message() {}


}
