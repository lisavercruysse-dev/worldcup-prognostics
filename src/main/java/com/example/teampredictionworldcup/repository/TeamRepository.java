package com.example.teampredictionworldcup.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teampredictionworldcup.model.Team;

public interface TeamRepository extends JpaRepository<Team, String>{
}
