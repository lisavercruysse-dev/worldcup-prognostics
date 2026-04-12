package com.example.teampredictionworldcup.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teampredictionworldcup.model.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, String>{

    List<Team> findTop10ByOrderByScoreDesc();
}
