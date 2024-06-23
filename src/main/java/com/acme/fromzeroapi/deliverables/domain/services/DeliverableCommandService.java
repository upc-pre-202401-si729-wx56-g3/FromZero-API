package com.acme.fromzeroapi.deliverables.domain.services;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.UpdateDeliverableStatusCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.UpdateDeveloperMessageCommand;

import java.util.List;
import java.util.Optional;

public interface DeliverableCommandService {
    Optional<Deliverable> handle(CreateDeliverableCommand command);
    void handle(List<CreateDeliverableCommand> commands);
    Optional<Deliverable> handle(UpdateDeveloperMessageCommand command);
    Optional<Deliverable> handle(UpdateDeliverableStatusCommand command);
}
