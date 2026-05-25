package com.example.teampredictionworldcup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login**", "/", "/home", "/css/**", "/403**", "/matches/{matchId}", "/teams/leaderboard/**").permitAll()
                        .requestMatchers("/matches", "/matches/form/**", "/matches/{matchId}/score", "/stadiums/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).usernameParameter("username").passwordParameter("password"))
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"))
                .logout(logout -> logout
                        .logoutUrl("/logout").logoutSuccessUrl("/")
                                .permitAll());
        return http.build();
    }

}
