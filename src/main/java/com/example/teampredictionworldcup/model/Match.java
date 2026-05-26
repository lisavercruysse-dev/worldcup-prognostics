package com.example.teampredictionworldcup.model;

import com.example.teampredictionworldcup.utils.LocalDateDeserializer;
import com.example.teampredictionworldcup.utils.LocalDateSerializer;
import com.example.teampredictionworldcup.utils.LocalTimeDeserializer;
import com.example.teampredictionworldcup.utils.LocalTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Getter @Setter
@JsonPropertyOrder({"match_id", "date", "startTime", "endTime", "stadium", "countryA", "countryB", "scoreA", "scoreB"})
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @JsonProperty("match_id")
    private int id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startTime;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime endTime;

    @ManyToOne
    private Stadium stadium;

    private String countryA;

    private String countryB;

    private Integer scoreA;

    private Integer scoreB;

    public Match(String countryA, String countryB, LocalDate date, Stadium stadium, LocalTime startTime, LocalTime endTime) {
        this.countryA = countryA;
        this.countryB = countryB;
        this.date = date;
        this.stadium = stadium;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
