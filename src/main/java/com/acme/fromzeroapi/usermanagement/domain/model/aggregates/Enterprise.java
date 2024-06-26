package com.acme.fromzeroapi.usermanagement.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Setter
    private String enterpriseName;
    @Setter
    private String description = "No description provided.";
    @Setter
    private String country = "No country provided.";
    @Setter
    private String ruc = "No RUC provided.";
    @Setter
    private String phone = "No phone provided.";
    @Setter
    private String website = "No website provided.";
    @Setter
    private String profileImgUrl = "https://cdn-icons-png.flaticon.com/512/3237/3237472.png";
    @Setter
    private String sector = "No sector provided.";

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Enterprise(
            User user,
            String enterpriseName,
            String description,
            String country,
            String ruc,
            String phone,
            String website,
            String profileImgUrl,
            String sector) {
        this.user = user;
        this.enterpriseName = enterpriseName;
        this.description = description;
        this.country = country;
        this.ruc = ruc;
        this.phone = phone;
        this.website = website;
        this.profileImgUrl = profileImgUrl;
        this.sector = sector;
    }

    public Enterprise() {

    }
}
