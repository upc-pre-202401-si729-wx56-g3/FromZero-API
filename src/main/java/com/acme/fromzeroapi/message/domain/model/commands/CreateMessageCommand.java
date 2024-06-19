package com.acme.fromzeroapi.message.domain.model.commands;

import java.util.Date;

public record CreateMessageCommand(String subject, String emailBody, String recipient, String sender, Date creationTime, Date sentTime) {
}
