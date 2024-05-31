package com.acme.fromzeroapi.projects.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String state;

    private Double progress;

    //private Long ownerId;
    @ManyToOne
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;

    //private Long candidateId;

    @ManyToMany
    @JoinTable(
            name = "project_candidates",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> candidates;
}
