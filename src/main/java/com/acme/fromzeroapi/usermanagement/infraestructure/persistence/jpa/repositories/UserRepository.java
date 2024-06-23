package com.acme.fromzeroapi.usermanagement.infraestructure.persistence.jpa.repositories;

import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
