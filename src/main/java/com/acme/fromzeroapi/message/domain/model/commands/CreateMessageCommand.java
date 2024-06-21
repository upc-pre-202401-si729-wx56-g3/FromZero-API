package com.acme.fromzeroapi.message.domain.model.commands;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;

import java.util.Date;

public record CreateMessageCommand(String subject, String emailBody, User recipient, User sender) {
}
