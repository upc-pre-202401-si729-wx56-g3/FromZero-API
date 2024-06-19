package com.acme.fromzeroapi.message.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.message.domain.model.aggregates.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
