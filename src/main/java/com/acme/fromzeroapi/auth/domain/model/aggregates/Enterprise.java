package com.acme.fromzeroapi.auth.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String enterpriseName;

    private String description = "No description provided.";
    private String country = "No country provided.";
    private String ruc = "No RUC provided.";
    private String phone = "No phone provided.";
    private String website = "No website provided.";
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";
    private String sector = "No sector provided.";

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
