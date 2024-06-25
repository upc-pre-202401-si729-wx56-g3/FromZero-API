package com.acme.fromzeroapi.support.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.support.domain.model.aggregates.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketsRepository extends JpaRepository<SupportTicket, Long> {

}
