package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "matches")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Date dateTime;

    @ManyToOne
    @NotNull
    private Stadium stadium;

    @NotBlank
    @NotEmpty
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z]+")
    private String countryA;

    @NotBlank
    @NotEmpty
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z]+")
    private String countryB;

    public Match(String countryA, String countryB, Date dateTime, Stadium stadium) {
        this.countryA = countryA;
        this.countryB = countryB;
        this.dateTime = dateTime;
        this.stadium = stadium;
    }
}
