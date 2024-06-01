package com.acme.fromzeroapi.projects.domain.model.aggregates;

import com.acme.fromzeroapi.developer.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.enterprise.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String state;

    private Double progress;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @OneToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_candidates",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    @JsonManagedReference
    private List<Developer> candidates;

    //many to many relationship
    //private List<ProgrammingLanguages> languages;

    //many to many relationship
    //private List<Frameworks> frameworks;

    public Project(CreateProjectCommand command){
        this.name=command.name();
        this.description=command.description();
        this.state="En busqueda";
        this.progress=0.0;
        this.enterprise=command.enterprise();
        this.developer=null;
    }

    public Project(){

    }
}
