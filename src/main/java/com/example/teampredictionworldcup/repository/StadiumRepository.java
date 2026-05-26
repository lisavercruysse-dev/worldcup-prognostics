package com.example.teampredictionworldcup.repository;

import com.example.teampredictionworldcup.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium,Integer> {

    @Query("SELECT s.capacity FROM Stadium s WHERE s.stadiumCode = :id")
    Optional<Integer> getCapacityByStadiumId(@Param("id") Integer id);
}
