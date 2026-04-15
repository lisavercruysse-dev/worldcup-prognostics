package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "prognostics")
@EqualsAndHashCode(of = "id")
@Getter
public class Prognostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Match match;

    @ManyToOne
    private Member member;

    private int goalsTeamA;
    private int goalsTeamB;

    public Prognostic(Match match, Member member, int goalsTeamA, int goalsTeamB) {
        this.match = match;
        this.member = member;
        member.getPrognostics().add(this);
        this.goalsTeamA = goalsTeamA;
        this.goalsTeamB = goalsTeamB;
    }
}
