package com.acme.fromzeroapi.auth.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String description = "No description provided.";
    private String country = "No country provided.";
    private String phone = "No phone provided.";
    private int completedProjects = 0;
    private String specialties = "No specialties provided.";
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
