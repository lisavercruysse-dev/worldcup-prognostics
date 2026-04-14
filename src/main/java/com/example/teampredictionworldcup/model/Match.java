package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @DateTimeFormat(style="MM")
    private LocalDateTime dateTime;

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

    public Match(String countryA, String countryB, LocalDateTime dateTime, Stadium stadium) {
        this.countryA = countryA;
        this.countryB = countryB;
        this.dateTime = dateTime;
        this.stadium = stadium;
    }
}
