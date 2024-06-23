package com.acme.fromzeroapi.auth.domain.model.aggregates;

import com.acme.fromzeroapi.auth.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    public User(CreateUserCommand command){
        this.username = command.username();
        this.password = command.password();
        this.email = command.email();
    }
    public User() {

    }
}
