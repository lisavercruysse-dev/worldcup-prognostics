package com.example.teampredictionworldcup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginGet() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @WithMockUser(username = "Jan")
    @ParameterizedTest
    @ValueSource(strings = {"/matches/1", "/prognostics/form/1", "/prognostics", "/teams", "/teams/create", "/teams/join", "/teams/leaderboard"})
    void testAccessWithUserRole(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "Jan", roles = {"ADMIN"})
    @ParameterizedTest
    @ValueSource(strings = {"/matches", "/matches/form/1", "/matches/form", "/matches/1/score", "/stadiums", "/stadiums/form"})
    void testAccessWithAdminRole(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "Jan", roles = {"ADMIN"})
    @ParameterizedTest
    @ValueSource(strings = {"/prognostics/form/1", "/prognostics", "/teams", "/teams/create", "/teams/join"})
    void testNoAccessWithWrongAdminRole(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "Jan", roles = {"USER"})
    @ParameterizedTest
    @ValueSource(strings = {"/matches", "/matches/form/1", "/matches/form", "/matches/1/score", "/stadiums", "/stadiums/form"})
    void testNoAccessWithWrongUserRole(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isForbidden());
    }

    @Test
    void testWrongPassword() throws Exception {
        mockMvc.perform(formLogin("/login")
                .user("username", "Jan")
                .password("password", "wrong"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    void testCorrectPassword() throws Exception {
        mockMvc.perform(formLogin("/login")
                .user("username", "Jan")
                .password("password", "12345678"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
