package com.example.teampredictionworldcup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TeamPredictionWorldCupApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TeamPredictionWorldCupApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("team-overview");
    }
}
