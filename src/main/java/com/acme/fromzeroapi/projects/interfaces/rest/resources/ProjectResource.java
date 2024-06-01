package com.acme.fromzeroapi.projects.interfaces.rest.resources;

import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;

import java.util.List;

public record ProjectResource(
        Long id, String name, String description, String state, Double progress,
        Long ownerId, Long developerId, List<Developer> candidatesList) {
    public ProjectResource{
        if(id==null || name==null || description==null){
            throw new NullPointerException("id is null");
        }
    }

    /**
     * Constructor for projects with the following parameters:
     * Long id
     * String name
     * String description
     * String state = "En busqueda"
     * String progress
     * Long ownerId
     * Long developerId = null
     * List< Developer> candidatesList = either full or empty
     */
    public ProjectResource(Long id,String name, String description, String state,
                           Double progress, Long ownerId,List<Developer> candidatesList){
        this(id,name,description,state,progress,ownerId,null,candidatesList);
    }
}
