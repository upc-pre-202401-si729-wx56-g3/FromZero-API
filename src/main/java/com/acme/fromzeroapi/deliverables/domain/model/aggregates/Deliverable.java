package com.acme.fromzeroapi.deliverables.domain.model.aggregates;

import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
//import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
//import java.util.Date;

@Getter
@Entity
public class Deliverable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public Deliverable(CreateDeliverableCommand command) {
        this.name=command.name();
        this.description=command.description();
        this.date=command.date();
        this.state="Pending";
        this.project=command.project();

    }

    public Deliverable() {

    }

}
