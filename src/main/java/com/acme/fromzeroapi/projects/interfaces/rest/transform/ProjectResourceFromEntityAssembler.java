package com.acme.fromzeroapi.projects.interfaces.rest.transform;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        //varios if con validaciones para hacer un return
        if("En busqueda".equals(entity.getState())){
            /*
             * Get Project Resource where State is "En busqueda"
             * Returns ProjectResource
             */
            return new ProjectResource(entity.getId(),entity.getName(),entity.getDescription(),
                    entity.getState(), entity.getProgress(),entity.getEnterprise().getId(),entity.getCandidates(),
                    entity.getLanguages(),entity.getFrameworks(),entity.getType(),
                    entity.getBudget(),entity.getMethodologies());
        }

        if("En progreso".equals(entity.getState())){
            return new ProjectResource(entity.getId(),entity.getName(),entity.getDescription(),
                    entity.getState(), entity.getProgress(),entity.getEnterprise().getId(),
                    entity.getDeveloper().getId(),entity.getCandidates(),
                    entity.getLanguages(),entity.getFrameworks(),entity.getType(),
                    entity.getBudget(),entity.getMethodologies());
        }

        throw new IllegalArgumentException("El estado no es valido");
        // si no conincide con algun estado, throw error
    }
}
