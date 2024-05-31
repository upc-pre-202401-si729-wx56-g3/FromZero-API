package com.acme.fromzeroapi.projects.domain.model.aggregates;

import jakarta.persistence.*;

@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /*@OneToMany(mappedBy = "enterprise")
    private Project project;*/

}
