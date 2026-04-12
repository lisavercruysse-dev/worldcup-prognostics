package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Setter
    @ManyToOne
    @JoinColumn(name = "team_name")
    private Team team;

    @OneToOne(mappedBy = "owner")
    private Team ownedTeam;

    private int score;

    public Member(String name) {
        this.name = name;
    }

}
