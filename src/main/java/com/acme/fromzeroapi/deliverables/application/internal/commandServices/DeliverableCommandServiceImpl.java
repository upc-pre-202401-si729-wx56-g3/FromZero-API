package com.acme.fromzeroapi.deliverables.application.internal.commandServices;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableCommandService;
import com.acme.fromzeroapi.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliverableCommandServiceImpl implements DeliverableCommandService {
    private final DeliverableRepository deliverableRepository;
    public DeliverableCommandServiceImpl(DeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
    }

    @Override
    public Optional<Deliverable> handle(CreateDeliverableCommand command) {
        var deliverable = new Deliverable(command);
        this.deliverableRepository.save(deliverable);
        return Optional.of(deliverable);
    }

}
