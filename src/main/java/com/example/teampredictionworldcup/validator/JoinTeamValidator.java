package com.example.teampredictionworldcup.validator;

import com.example.teampredictionworldcup.dto.response.JoinTeamInputDTO;
import com.example.teampredictionworldcup.dto.response.TeamDTO;
import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component("joinTeamValidator")
@RequiredArgsConstructor
public class JoinTeamValidator implements Validator {
    private final TeamService teamService;

    @Override
    public boolean supports(Class<?> klass) {return JoinTeamInputDTO.class.isAssignableFrom(klass);}

    @Override
    public void validate(Object target, Errors errors) {
       JoinTeamInputDTO joinTeamDTO = (JoinTeamInputDTO) target;

        TeamDTO dto = teamService.getTeamByName(joinTeamDTO.teamName());

        if (joinTeamDTO.teamName() == null || joinTeamDTO.teamName().isEmpty()) {
            errors.rejectValue("teamName", "team.nameEmpty", "Team name is required");
            return;
        }

        if (dto == null) {
            errors.rejectValue("teamName", "team.notFound", "Team does not exist");
            return;
        }

        if (joinTeamDTO.inviteCode() == null || joinTeamDTO.inviteCode().isEmpty()) {
            errors.rejectValue("inviteCode", "inviteCode.empty", "Invite code is required");
        }

        else if (!dto.inviteCode().equals(joinTeamDTO.inviteCode())) {
            errors.rejectValue("inviteCode", "inviteCode.invalid", "Invite code is incorrect");
        }
    }
}