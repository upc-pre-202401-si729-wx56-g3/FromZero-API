package com.acme.fromzeroapi.message.interfaces.rest.resources;

import java.util.Date;

public record CreateMessageResource(String subject, String emailBody, String recipient, String sender, Date creationTime, Date sentTime) {
}