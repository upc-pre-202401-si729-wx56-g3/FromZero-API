package com.acme.fromzeroapi.home.domain.model.aggregates;

import com.acme.fromzeroapi.home.domain.model.commands.CreateDesarrolladorCommand;
import com.acme.fromzeroapi.home.domain.model.commands.CreateEmpresarioCommand;
import com.acme.fromzeroapi.shared.model.aggregates.AuditableAbstractAggregateRoot;

public class Empresario extends AuditableAbstractAggregateRoot<Empresario> {
    String _country;
    int _businessName;
    int _cellphone;
    String _email;
    String _website;
    String _section;

    public Empresario() {
    }

    public Empresario(String country, int businessName, int cellphone, String email, String website, String section) {
        _country = country;
        _businessName = businessName;
        _cellphone = cellphone;
        _email = email;
        _website = website;
        _section = section;
    }

    public Empresario(CreateEmpresarioCommand command) {
        this._country = command.country();
        this._businessName = command.businessName();
        this._cellphone = command.cellphone();
        this._email = command.email();
        this._website = command.website();
        this._section = command.section();
    }


    public String getCountry() {
        return _country;
    }

    public void setCountry(String country) {
        _country = country;
    }

    public int getBusinessName() {
        return _businessName;
    }

    public void setBusinessName(int businessName) {
        _businessName = businessName;
    }

    public int getCellphone() {
        return _cellphone;
    }

    public void setCellphone(int cellphone) {
        _cellphone = cellphone;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getWebsite() {
        return _website;
    }

    public void setWebsite(String website) {
        _website = website;
    }

    public String getSection() {
        return _section;
    }

    public void setSection(String section) {
        _section = section;
    }
}
