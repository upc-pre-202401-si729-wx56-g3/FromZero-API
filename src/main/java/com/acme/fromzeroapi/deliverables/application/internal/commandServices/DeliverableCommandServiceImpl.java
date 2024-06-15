package com.acme.fromzeroapi.deliverables.application.internal.commandServices;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.UpdateDeliverableStatusCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.UpdateDeveloperMessageCommand;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableCommandService;
import com.acme.fromzeroapi.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Override
    public void handle(List<CreateDeliverableCommand> commands) {
        //List<Deliverable> deliverablesList  = new ArrayList<>();
        commands.forEach(command -> {
            Optional<Deliverable> deliverable = this.handle(command);
            if(deliverable.isEmpty())throw new IllegalArgumentException();
            this.deliverableRepository.save(deliverable.get());
        });

    }

    @Override
    public Optional<Deliverable> handle(UpdateDeveloperMessageCommand command) {
        try {
            var deliverable = this.deliverableRepository.findById(command.deliverableId());
            if(deliverable.isEmpty())throw new IllegalArgumentException();
            deliverable.get().setDeveloperMessage(command.message());
            deliverable.get().setState("Awaiting Review");
            this.deliverableRepository.save(deliverable.get());
            return deliverable;
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Deliverable> handle(UpdateDeliverableStatusCommand command) {
        try {
            var deliverable = this.deliverableRepository.findById(command.deliverableId());
            if(deliverable.isEmpty())throw new IllegalArgumentException();
            if (command.accepted()){
                deliverable.get().setState("Completed");
            }else deliverable.get().setState("Rejected");
            this.deliverableRepository.save(deliverable.get());
            return deliverable;
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }

    }

}
