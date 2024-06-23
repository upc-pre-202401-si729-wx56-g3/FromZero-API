package com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
