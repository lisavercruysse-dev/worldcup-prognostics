package com.example.teampredictionworldcup.repository;

import com.example.teampredictionworldcup.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
