package com.example.teampredictionworldcup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stadiums")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stadium {

    @Id
    private int stadiumCode;

    @NotEmpty
    @Size(min = 4)
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$")
    private String name;

    @NotEmpty
    @Size(min = 4)
    @Pattern(regexp = "^[a-zA-Z]+")
    private String city;

    public Stadium(int stadiumCode, String name, String city) {
        this.stadiumCode = stadiumCode;
        this.name = name;
        this.city = city;
    }

}
