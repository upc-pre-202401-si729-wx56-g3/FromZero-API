package com.acme.fromzeroapi.highlightprojects.domain.services;

import com.acme.fromzeroapi.highlightprojects.domain.model.aggregates.HighlightProject;
import com.acme.fromzeroapi.highlightprojects.domain.model.queries.GetAllHighlightProjectsQuery;

import java.util.List;

public interface HighlightProjectsQueryService {
    List<HighlightProject> handle(GetAllHighlightProjectsQuery query);
}
