package com.acme.fromzeroapi.auth.application.internal.commandservices;

import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.domain.model.queries.GetAllUsersQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetUserByEmailQuery;
import com.acme.fromzeroapi.auth.domain.model.queries.GetUserByIdQuery;
import com.acme.fromzeroapi.auth.domain.services.UserQueryService;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }
}
