package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Table(name = "matches")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @ManyToOne
    @NotNull
    private Stadium stadium;

    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z]+")
    private String countryA;

    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z]+")
    private String countryB;

    public Match(String countryA, String countryB, LocalDate date, Stadium stadium, LocalTime startTime, LocalTime endTime) {
        this.countryA = countryA;
        this.countryB = countryB;
        this.date = date;
        this.stadium = stadium;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
