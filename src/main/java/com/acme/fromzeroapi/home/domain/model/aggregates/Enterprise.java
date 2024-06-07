package com.acme.fromzeroapi.home.domain.model.aggregates;

import com.acme.fromzeroapi.home.domain.model.commands.CreateEnterpriseCommand;
import com.acme.fromzeroapi.shared.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Enterprise extends AuditableAbstractAggregateRoot<Enterprise> {

    private String name;
    private String description;
    private String country;
    private String ruc;
    private String phone;
    private String website;
    private String profileImgUrl;
    private String sector;


    public Enterprise() {
    }

    public Enterprise(CreateEnterpriseCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.country = command.country();
        this.ruc = command.ruc();
        this.phone = command.phone();
        this.website = command.website();
        this.profileImgUrl = command.profileImgUrl();
        this.sector = command.sector();
    }
}
