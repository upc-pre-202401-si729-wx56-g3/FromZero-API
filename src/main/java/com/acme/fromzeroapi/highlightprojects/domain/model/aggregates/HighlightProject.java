package com.acme.fromzeroapi.highlightprojects.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class HighlightProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String website_url;

    @Column(nullable = false)
    private String image_url;

    @Column(nullable = false)
    private float calification;

    @Column(nullable = false)
    private String type;

    public HighlightProject() {

    }
}
