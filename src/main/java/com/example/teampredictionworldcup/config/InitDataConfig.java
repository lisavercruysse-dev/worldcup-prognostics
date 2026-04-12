package com.example.teampredictionworldcup.config;

import com.example.teampredictionworldcup.repository.MemberRepository;
import com.example.teampredictionworldcup.repository.TeamRepository;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class InitDataConfig implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) {

        Member jan = new Member("Jan");
        Member piet  = new Member("Piet");
        Member hannah = new Member("Hannah");

        //Members Team A
        Member sam = new Member("Sam");
        Member lilly = new Member("Lilly");

        //Members Team B
        Member tom = new Member("Tom");
        Member luca = new Member("Luca");

        //Members Team C
        Member pieter = new Member("Pieter");
        Member katrien = new Member("Katrien");

        Team teamA = new Team("Team A", jan);
        Team teamB = new Team("Team B", piet);
        Team teamC = new Team("Team C", hannah);


        teamA.addMember(sam);
        teamA.addMember(lilly);

        teamB.addMember(tom);
        teamB.addMember(luca);

        teamC.addMember(pieter);
        teamC.addMember(katrien);

        teamRepository.saveAll(List.of(teamA, teamB, teamC));
    }
}
