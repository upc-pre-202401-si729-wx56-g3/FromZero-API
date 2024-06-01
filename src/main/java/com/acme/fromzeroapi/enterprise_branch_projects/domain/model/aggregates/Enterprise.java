package com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Enterprise(String name) {this.name = name;}
    public Enterprise(){}
    /*@OneToMany(mappedBy = "enterprise")
    private Project project;*/

}
