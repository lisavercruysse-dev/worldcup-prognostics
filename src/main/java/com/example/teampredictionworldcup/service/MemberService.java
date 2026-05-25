package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.JoinTeamInputDTO;
import com.example.teampredictionworldcup.dto.response.MemberDTO;
import com.example.teampredictionworldcup.dto.response.TeamMinimalDTO;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.Team;
import com.example.teampredictionworldcup.repository.MemberRepository;
import com.example.teampredictionworldcup.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    private static MemberDTO toDTO(Member m) {
        return new MemberDTO(m.getId(), m.getName(), m.getTeam() != null ? m.getTeam().getTeamName() : null, m.getScore());
    }

    public MemberDTO getMemberById(int id) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member != null) {
            String teamName = member.getTeam() != null ? member.getTeam().getTeamName() : null;
            return new MemberDTO(member.getId(), member.getName(),teamName, member.getScore());
        }
        return null;
    }

    public List<MemberDTO> getMembersFromTeam(String teamName) {
        List<Member> members = memberRepository.findByTeamTeamName(teamName);
        return members.stream().map(MemberService::toDTO).toList();
    }

    public MemberDTO getMemberByName(String username) {
        return toDTO(memberRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("No username found: %s".formatted(username))));
    }
}

