package com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.home.domain.model.aggregates.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesarrolladorRepository  extends JpaRepository<Desarrollador, Long> {

    Optional<Desarrollador> findByEmail(String email);

    List<Desarrollador> findAll();
}
