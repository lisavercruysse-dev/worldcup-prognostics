package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
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
    private int id;

    private Date dateTime;

    @ManyToOne
    private Stadium stadium;

    private String countryA;
    private String countryB;

    public Match(String countryA, String countryB, Date dateTime, Stadium stadium) {
        this.countryA = countryA;
        this.countryB = countryB;
        this.dateTime = dateTime;
        this.stadium = stadium;
    }
}
