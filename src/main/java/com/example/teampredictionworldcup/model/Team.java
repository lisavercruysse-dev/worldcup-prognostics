package com.example.teampredictionworldcup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
@EqualsAndHashCode(of = "teamName")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @Column(name = "team_name")
    @NotBlank
    @Size(min = 4)
    @Pattern(regexp = "^[A-Za-z]+$")
    String teamName;

    @Column(name = "invite_code")
    private String inviteCode;

    @OneToOne
    @JoinColumn(name = "owner_id")
    @NotNull
    private Member owner;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    private int score;

    public Team(String teamName, Member owner) {
        this.teamName = teamName;
        this.owner = owner;
        owner.setTeam(this);
        addMember(owner);
        score = 0;
    }

    private void setInviteCode () {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int randomChar = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(randomChar));
        }
        this.inviteCode = sb.toString();
    }

    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }
}
