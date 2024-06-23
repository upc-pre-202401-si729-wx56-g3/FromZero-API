package com.acme.fromzeroapi.deliverables.application.internal.queryServices;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import com.acme.fromzeroapi.deliverables.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.acme.fromzeroapi.deliverables.domain.model.queries.GetDeliverableByIdQuery;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableQueryService;
import com.acme.fromzeroapi.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliverableQueryServiceImpl implements DeliverableQueryService {
    private final DeliverableRepository deliverableRepository;

    public DeliverableQueryServiceImpl(DeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
    }

    @Override
    public List<Deliverable> handle(GetAllDeliverablesByProjectIdQuery query) {
        return this.deliverableRepository.findAllByProject(query.project());
    }

    @Override
    public Optional<Deliverable> handle(GetDeliverableByIdQuery query) {
        return this.deliverableRepository.findById(query.id());
    }
}
