package com.example.teampredictionworldcup.client;

import com.example.teampredictionworldcup.model.Match;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RestClient {

    private final String SERVER_URI = "http://localhost:8080/api";

    private WebClient webClient = WebClient.builder().baseUrl(SERVER_URI).build();

    public RestClient() throws Exception{
        System.out.println("GET STADIUM CAPACITY (1001) ***********************");
        getStadiumCapacity(1001).doOnNext(System.out::println).block();

        System.out.println("GET MATCHES BY DATE (13, 06, 2026) ***********************");
        getMatchesByDate("13-06-2026").doOnNext(this::printMatchData).blockLast();
    }

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

    private void printMatchData(Match match) {
        System.out.printf("Id=%s, CountryA=%s, CountryB=%s, date=%s, stadium=%s, starttime=%s, endtime=%s %n",
                match.getId(), match.getCountryA(), match.getCountryB(), match.getDate(), match.getStadium().getName(), match.getStartTime(), match.getEndTime());
    }

}
