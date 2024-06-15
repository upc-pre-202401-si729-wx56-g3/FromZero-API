package com.acme.fromzeroapi.deliverables.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
}
