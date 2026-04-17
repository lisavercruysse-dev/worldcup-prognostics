package com.example.teampredictionworldcup.validator;

import com.example.teampredictionworldcup.dto.response.TeamInputDTO;
import com.example.teampredictionworldcup.repository.TeamRepository;
import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("createTeamValidator")
@RequiredArgsConstructor
public class CreateTeamValidator implements Validator {

    private final TeamRepository teamRepository;

    @Override
    public boolean supports(Class<?> klass) {
        return TeamInputDTO.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors){
        TeamInputDTO teamInputDTO = (TeamInputDTO) target;

        if (teamRepository.existsById(teamInputDTO.teamName())) {
            errors.rejectValue("teamName", "teamName.taken", "A team with this name already exists");
        }
    }
}
