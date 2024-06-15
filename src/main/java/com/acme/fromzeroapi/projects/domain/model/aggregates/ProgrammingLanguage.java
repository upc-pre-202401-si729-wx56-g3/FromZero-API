package com.acme.fromzeroapi.projects.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "languages")
    @JsonBackReference
    private List<Project> projects;

    public ProgrammingLanguage(String name){
        this.name=name;
    }
    public ProgrammingLanguage(){

    }

}
