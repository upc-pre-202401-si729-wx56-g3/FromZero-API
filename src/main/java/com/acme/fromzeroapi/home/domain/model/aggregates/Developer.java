package com.acme.fromzeroapi.home.domain.model.aggregates;

import com.acme.fromzeroapi.home.domain.model.commands.CreateDeveloperCommand;
import com.acme.fromzeroapi.shared.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Developer extends AuditableAbstractAggregateRoot<Developer> {

    private String firstName;
    private String LastName;
    private String description;
    private String country;
    private String phone;
    private int completedProjects;
    private String specialties;
    private String profileImgUrl;
    private Long userId;

    public Developer() {
    }

    public Developer(CreateDeveloperCommand command) {
        this.firstName = command.firstName();
        this.LastName = command.LastName();
        this.description = command.description();
        this.country = command.country();
        this.phone = command.phone();
        this.completedProjects = command.completedProjects();
        this.specialties = command.specialties();
        this.profileImgUrl = command.profileImgUrl();
        this.userId = command.userId();
    }
}
