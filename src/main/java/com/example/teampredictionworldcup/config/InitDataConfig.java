package com.example.teampredictionworldcup.config;

import com.example.teampredictionworldcup.model.*;
import com.example.teampredictionworldcup.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private static final String BCRYPTED_PASSWORD = "$2a$12$0ZcT8xPFjOVmewRj.WeILu7VFNYnsUwliliLF5VYiDxHIPMk2vD/.";

    @Override
    public void run(String... args) {

        Member jan = new Member("Jan", BCRYPTED_PASSWORD, UserAuthority.USER);
        Member piet  = new Member("Piet", BCRYPTED_PASSWORD, UserAuthority.USER);
        Member hannah = new Member("Hannah", BCRYPTED_PASSWORD, UserAuthority.USER);

        //Members Team A
        Member sam = new Member("Sam", BCRYPTED_PASSWORD, UserAuthority.USER);
        Member lilly = new Member("Lilly", BCRYPTED_PASSWORD, UserAuthority.USER);

        //Members Team B
        Member tom = new Member("Tom", BCRYPTED_PASSWORD, UserAuthority.USER);
        Member luca = new Member("Luca", BCRYPTED_PASSWORD, UserAuthority.USER);

        //Members Team C
        Member pieter = new Member("Pieter", BCRYPTED_PASSWORD, UserAuthority.USER);
        Member katrien = new Member("Katrien", BCRYPTED_PASSWORD, UserAuthority.USER);

        //Other
        Member bert = new Member("Bert", BCRYPTED_PASSWORD, UserAuthority.USER);
        Member geert = new Member("Geert", BCRYPTED_PASSWORD, UserAuthority.ADMIN);

        memberRepository.saveAll(List.of(jan, piet, hannah, sam, lilly, tom, luca, pieter, katrien, bert, geert));

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

        Stadium s1 = new Stadium(1001, "National Arena", "London", 50000);
        Stadium s2 = new Stadium(1002, "Olympic Stadium", "Paris", 60000);
        Stadium s3 = new Stadium(1003, "Victory Stadium", "Madrid", 90000);
        Stadium s4 = new Stadium(1004, "Grand Arena", "Rome", 45000);
        Stadium s5 = new Stadium(1005, "Sunshine Park", "Lisbon", 50000);

        stadiumRepository.saveAll(List.of(s1, s2, s3, s4, s5));

        Match m1 = new Match("Brazil", "Argentina",
                LocalDate.of(2026, 3, 8),
                s1, LocalTime.of(18, 0), LocalTime.of(20, 0));

        Match m2 = new Match("France", "Germany",
                LocalDate.of(2026, 6, 12),
                s2, LocalTime.of(14, 0),  LocalTime.of(16, 0));

        Match m3 = new Match("Spain", "Portugal",
                LocalDate.of(2026, 6, 12),
                s3, LocalTime.of(14, 0),  LocalTime.of(16, 0));

        Match m4 = new Match("England", "Italy",
                LocalDate.of(2026, 6, 13),
                s4, LocalTime.of(15, 30),  LocalTime.of(17, 30));

        Match m5 = new Match("Netherlands", "Belgium",
                LocalDate.of(2026, 6, 13),
                s5, LocalTime.of(19, 30),  LocalTime.of(21, 30));

        Match m6 = new Match("Netherlands", "Belgium",
                LocalDate.of(2026, 4, 25),
                s5, LocalTime.of(18, 0),  LocalTime.of(20, 0));

        Match m7 = new Match("Netherlands", "Belgium",
                LocalDate.of(2026, 5, 23),
                s5, LocalTime.of(17, 38),  LocalTime.of(20, 0));

        matchRepository.saveAll(List.of(m1, m2, m3, m4, m5, m6, m7));

        Prognostic p1 = new Prognostic(m1, piet, 1, 1);
        Prognostic p2 = new Prognostic(m1, jan, 0, 1);
        Prognostic p3 = new Prognostic(m1, hannah, 0, 0);
        Prognostic p4 = new Prognostic(m1, sam, 2, 2);
        Prognostic p5 = new Prognostic(m1, tom, 1, 1);

        prognosticRepository.saveAll(List.of(p1, p2, p3, p4, p5));

    }
}
