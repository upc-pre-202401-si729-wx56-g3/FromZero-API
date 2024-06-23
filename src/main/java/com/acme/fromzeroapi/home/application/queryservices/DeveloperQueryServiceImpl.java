package com.acme.fromzeroapi.home.application.queryservices;

import com.acme.fromzeroapi.home.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.home.domain.model.queries.GetAllDevelopersQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperByCountryQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperByIdQuery;
import com.acme.fromzeroapi.home.domain.model.queries.GetDeveloperBySpecialityQuery;
import com.acme.fromzeroapi.home.domain.services.DeveloperQueryService;
import com.acme.fromzeroapi.home.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperQueryServiceImpl implements DeveloperQueryService {

    private final DeveloperRepository developerRepository;

    public DeveloperQueryServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByCountryQuery query) {
        return developerRepository.findByCountry(query.country());
    }

    @Override
    public Optional<Developer> handle(GetDeveloperBySpecialityQuery query) {
        return developerRepository.findBySpecialties(query.specialties());
    }

    @Override
    public Optional<Developer> handle(GetDeveloperByIdQuery query) {
        return developerRepository.findById(query.Id());
    }

    @Override
    public List<Developer> handle(GetAllDevelopersQuery query) {
        return developerRepository.findAll();
    }
}
