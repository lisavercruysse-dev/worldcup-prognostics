package com.example.teampredictionworldcup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.teampredictionworldcup.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByTeamTeamName(String teamName);

    Optional<Member> findByName(String username);
}
