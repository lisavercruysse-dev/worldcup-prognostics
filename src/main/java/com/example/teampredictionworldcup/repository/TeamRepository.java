package com.example.teampredictionworldcup.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teampredictionworldcup.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, String>{

    List<Team> findTop10ByOrderByScoreDesc();
    Team findByMembersId(int memberId);

    Optional<Team> findTeamByTeamName(String teamName);
}
