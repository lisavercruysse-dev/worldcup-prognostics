package com.example.teampredictionworldcup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login**", "/", "/home", "/css/**", "/403**", "/teams/leaderboard/**").permitAll()
                        .requestMatchers("/matches/form", "/matches/form/**", "/matches/*/score", "/stadiums/**", "/matches").hasRole("ADMIN")
                        .requestMatchers("/matches/*").permitAll()
                        .anyRequest().hasRole("USER"))
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).usernameParameter("username").passwordParameter("password"))
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"))
                .logout(logout -> logout
                        .logoutUrl("/logout").logoutSuccessUrl("/")
                                .permitAll());
        return http.build();
    }

}
