package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 2)
    @Pattern(regexp = "^[a-zA-Z]+")
    private String name;

    @Setter
    @ManyToOne
    @JoinColumn(name = "team_name")
    private Team team;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserAuthority authority;

    @OneToOne(mappedBy = "owner")
    private Team ownedTeam;

    private int score;

    @OneToMany(mappedBy = "member")
    private List<Prognostic> prognostics = new ArrayList<>();

    public Member(String name, String password, UserAuthority authority) {
        this.name = name;
        this.password = password;
        this.authority = authority;
        this.score = 0;
    }

    public void updateScore(int score) {
        this.score += score;
    }

}
