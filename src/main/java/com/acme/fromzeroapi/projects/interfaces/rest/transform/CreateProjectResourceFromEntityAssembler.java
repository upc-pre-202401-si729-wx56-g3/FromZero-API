package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.CreateProjectResource;

import java.util.List;
import java.util.stream.Collectors;

public class CreateProjectResourceFromEntityAssembler {
    public static CreateProjectResource toResourceFromEntity(Project entity) {
        List<Integer> languageIds = entity.getLanguages().stream()
                .map(ProgrammingLanguage::getId)
                .collect(Collectors.toList());

        // Transformar la lista de frameworks a una lista de IDs
        List<Integer> frameworkIds = entity.getFrameworks().stream()
                .map(Framework::getId)
                .collect(Collectors.toList());
        return new CreateProjectResource(entity.getName(), entity.getDescription(), entity.getEnterprise().getId(),
                languageIds,frameworkIds,entity.getType(),entity.getBudget(),entity.getMethodologies());
    }
}
