package com.example.teampredictionworldcup.config;

import com.example.teampredictionworldcup.model.*;
import com.example.teampredictionworldcup.repository.*;
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
    private final PrognosticRepository prognosticRepository;

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

        Member bert = new Member("Bert");

        memberRepository.saveAll(List.of(jan, piet, hannah, sam, lilly, tom, luca, pieter, katrien));

        Team teamA = new Team("TeamA", jan);
        Team teamB = new Team("TeamB", piet);
        Team teamC = new Team("TeamC", hannah);

        teamRepository.saveAll(List.of(teamA, teamB, teamC));

        teamA.addMember(sam);
        teamA.addMember(lilly);

        teamB.addMember(tom);
        teamB.addMember(luca);

        teamC.addMember(pieter);
        teamC.addMember(katrien);

        memberRepository.saveAll(List.of(sam, lilly, tom, luca, pieter, katrien, bert));

        Stadium s1 = new Stadium(1001, "National Arena", "London");
        Stadium s2 = new Stadium(1002, "Olympic Stadium", "Paris");
        Stadium s3 = new Stadium(1003, "Victory Stadium", "Madrid");
        Stadium s4 = new Stadium(1004, "Grand Arena", "Rome");
        Stadium s5 = new Stadium(1005, "Sunshine Park", "Lisbon");

        stadiumRepository.saveAll(List.of(s1, s2, s3, s4, s5));

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

        matchRepository.saveAll(List.of(m1, m2, m3, m4, m5));

        Prognostic p1 = new Prognostic(m1, piet, 1, 2);
        Prognostic p2 = new Prognostic(m1, jan, 2, 1);
        Prognostic p3 = new Prognostic(m1, hannah, 0, 0);
        Prognostic p4 = new Prognostic(m1, sam, 3, 1);
        Prognostic p5 = new Prognostic(m1, tom, 1, 1);

        prognosticRepository.saveAll(List.of(p1, p2, p3, p4, p5));

    }
}
