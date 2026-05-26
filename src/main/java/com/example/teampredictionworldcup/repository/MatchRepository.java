package com.example.teampredictionworldcup.repository;

import com.example.teampredictionworldcup.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findByDate(LocalDate localDate);
}
