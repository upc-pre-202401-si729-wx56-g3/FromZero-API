package com.acme.fromzeroapi.projects.domain.services;

import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.domain.model.queries.GetProgrammingLanguageByIdQuery;

import java.util.Optional;

public interface ProgrammingLanguagesQueryService {
    Optional<ProgrammingLanguage> handle(GetProgrammingLanguageByIdQuery query);
}
