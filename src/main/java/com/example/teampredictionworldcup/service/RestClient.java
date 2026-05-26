package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.model.Match;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RestClient {

    private final String SERVER_URI = "http://localhost:8080/api";

    private WebClient webClient = WebClient.builder().baseUrl(SERVER_URI).build();

    private Mono<Integer> getStadiumCapacity(int stadiumId) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/stadiums/{id}/capacity").build(stadiumId))
                .retrieve()
                .bodyToMono(Integer.class);
    }

    private Flux<Match> getMatchesByDate(String date) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/matches/{date}").build(date))
                .retrieve()
                .bodyToFlux(Match.class);
    }

}
