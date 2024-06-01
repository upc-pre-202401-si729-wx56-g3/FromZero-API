package com.acme.fromzeroapi.highlightprojects.application.internal.queryservices;

import com.acme.fromzeroapi.highlightprojects.domain.model.aggregates.HighlightProject;
import com.acme.fromzeroapi.highlightprojects.domain.model.queries.GetAllHighlightProjectsQuery;
import com.acme.fromzeroapi.highlightprojects.domain.services.HighlightProjectsQueryService;
import com.acme.fromzeroapi.highlightprojects.infraestructure.persistence.jpa.repositories.HighlightProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighlightProjectQueryServiceImpl implements HighlightProjectsQueryService {
    private final HighlightProjectRepository highlightProjectRepository;

    public HighlightProjectQueryServiceImpl(HighlightProjectRepository highlightProjectRepository) {
        this.highlightProjectRepository = highlightProjectRepository;
    }
    @Override
    public List<HighlightProject> handle(GetAllHighlightProjectsQuery query) {
        return highlightProjectRepository.findAll();
    }
}
