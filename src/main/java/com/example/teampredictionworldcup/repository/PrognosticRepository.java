package com.example.teampredictionworldcup.repository;

import com.example.teampredictionworldcup.model.Prognostic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrognosticRepository extends JpaRepository<Prognostic, Integer> {

    Optional<Prognostic> findByMatchIdAndMemberId(int matchId, int memberId);
}
