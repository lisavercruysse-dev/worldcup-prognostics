package com.example.teampredictionworldcup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.teampredictionworldcup.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
