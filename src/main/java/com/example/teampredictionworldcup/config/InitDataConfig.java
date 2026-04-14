package com.example.teampredictionworldcup.config;

import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.repository.MatchRepository;
import com.example.teampredictionworldcup.repository.MemberRepository;
import com.example.teampredictionworldcup.repository.StadiumRepository;
import com.example.teampredictionworldcup.repository.TeamRepository;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class InitDataConfig implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final StadiumRepository stadiumRepository;
    private final MatchRepository matchRepository;

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

        Team teamA = new Team("TeamA", jan);
        Team teamB = new Team("TeamB", piet);
        Team teamC = new Team("TeamC", hannah);

        Stadium s1 = new Stadium(1001, "National Arena", "London");
        Stadium s2 = new Stadium(1002, "Olympic Stadium", "Paris");
        Stadium s3 = new Stadium(1003, "Victory Stadium", "Madrid");
        Stadium s4 = new Stadium(1004, "Grand Arena", "Rome");
        Stadium s5 = new Stadium(1005, "Sunshine Park", "Lisbon");

        Match m1 = new Match("Brazil", "Argentina",
                LocalDateTime.of(2026, 6, 10, 18, 0),
                s1);

        Match m2 = new Match("France", "Germany",
                LocalDateTime.of(2026, 6, 11, 20, 0),
                s2);

        Match m3 = new Match("Spain", "Portugal",
                LocalDateTime.of(2026, 6, 12, 17, 30),
                s3);

        Match m4 = new Match("England", "Italy",
                LocalDateTime.of(2026, 6, 13, 21, 0),
                s4);

        Match m5 = new Match("Netherlands", "Belgium",
                LocalDateTime.of(2026, 6, 14, 19, 0),
                s5);

        teamA.addMember(sam);
        teamA.addMember(lilly);

        teamB.addMember(tom);
        teamB.addMember(luca);

        teamC.addMember(pieter);
        teamC.addMember(katrien);

        teamRepository.saveAll(List.of(teamA, teamB, teamC));
        stadiumRepository.saveAll(List.of(s1, s2, s3, s4, s5));
        matchRepository.saveAll(List.of(m5, m3, m4, m1, m2));
    }
}
