package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MemberDTO;
import com.example.teampredictionworldcup.dto.response.TeamMinimalDTO;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDTO getMemberById(int id) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member != null) {
            String teamName = member.getTeam() != null ? member.getTeam().getTeamName() : null;
            return new MemberDTO(member.getId(), member.getName(),teamName, member.getScore());
        }
        return null;
    }
}

