package com.acme.fromzeroapi.developer.domain.model.aggregates;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "candidates")
    private List<Project> projects;
    public Developer(String name){this.name = name;}
    public Developer(){}
}
