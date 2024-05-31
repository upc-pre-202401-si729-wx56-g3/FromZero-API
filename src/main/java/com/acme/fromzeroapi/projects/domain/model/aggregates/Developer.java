package com.acme.fromzeroapi.projects.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "candidates")
    private List<Project> projects;

}
