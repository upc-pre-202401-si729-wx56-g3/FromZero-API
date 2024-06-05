package com.acme.fromzeroapi.home.domain.model.aggregates;

import com.acme.fromzeroapi.home.domain.model.commands.CreateDesarrolladorCommand;
import com.acme.fromzeroapi.shared.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Desarrollador extends AuditableAbstractAggregateRoot<Desarrollador> {

    private String country;
    private int cellphone;
    private String email;
    private int projectQuantity;
    private String specialities;

    public Desarrollador() {
    }

    public Desarrollador(String _country, int _cellphone, String _email, int _projectQuantity, String _specialities) {
        country = _country;
        cellphone = _cellphone;
        email = _email;
        projectQuantity = _projectQuantity;
        specialities = _specialities;
    }

    public Desarrollador(CreateDesarrolladorCommand command) {
        this.country = command.country();
        this.cellphone = command.cellphone();
        this.email = command.email();
        this.projectQuantity = command.projectQuantity();
        this.specialities = command.specialities();
    }

}
