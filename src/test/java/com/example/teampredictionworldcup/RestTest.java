package com.example.teampredictionworldcup;

import com.example.teampredictionworldcup.config.SecurityConfig;
import com.example.teampredictionworldcup.controller.RestController;
import com.example.teampredictionworldcup.exceptions.StadiumNotFoundException;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.StadiumService;
import com.example.teampredictionworldcup.validator.CreateTeamValidator;
import com.example.teampredictionworldcup.validator.JoinTeamValidator;
import com.example.teampredictionworldcup.validator.MatchValidator;
import com.example.teampredictionworldcup.validator.StadiumValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestController.class)
@Import(SecurityConfig.class)
public class RestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean private StadiumService stadiumService;
    @MockitoBean private MatchService matchService;
    
    @MockitoBean private StadiumValidator stadiumValidator;
    @MockitoBean private CreateTeamValidator createTeamValidator;
    @MockitoBean private JoinTeamValidator joinTeamValidator;
    @MockitoBean private MatchValidator matchValidator;

    @Test
    void getStadiumCapacityExistingStadiumTest() throws Exception {
        mockMvc.perform(get("/api/stadiums/1001/capacity")).andExpect(status().isOk());
    }

    @Test
    void getStadiumCapacityNonExistingStadiumTest_ThrowsException() throws Exception {
        when(stadiumService.getCapacityByStadiumId(9000))
                .thenThrow(new StadiumNotFoundException(9000));
        mockMvc.perform(get("/api/stadiums/9000/capacity")).andExpect(status().isNotFound());
    }

    @Test
    void getMatchesFromDateTest() throws Exception {
        mockMvc.perform(get("/api/matches/13-06-2026")).andExpect(status().isOk());
    }
}
