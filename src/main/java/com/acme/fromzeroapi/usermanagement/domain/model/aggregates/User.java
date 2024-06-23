package com.acme.fromzeroapi.usermanagement.domain.model.aggregates;

import com.acme.fromzeroapi.usermanagement.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userType;


    public User(CreateUserCommand command){
        this.email = command.email();
        this.password = command.password();
        this.userType = command.userType();
    }
    public User() {

    }
    //borrar luego
    public User(String email, String password, String userType){
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
