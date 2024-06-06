package com.acme.fromzeroapi.projects.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Framework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "frameworks")
    @JsonBackReference
    private List<Project> projects;
    public Framework(String name){
        this.name = name;
    }
    public Framework() {

    }

}
