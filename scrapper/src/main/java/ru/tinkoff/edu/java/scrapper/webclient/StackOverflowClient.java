package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.StackoverflowAnswers;
import ru.tinkoff.edu.java.scrapper.dto.response.StackoverflowQuestion;
import ru.tinkoff.edu.java.scrapper.dto.response.StackoverflowQuestions;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StackOverflowClient {
    private final static String BASE_URL = "https://api.stackexchange.com/";
    private final WebClient webClient;

    public StackOverflowClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public StackOverflowClient(String url) {
        webClient = WebClient.create(url);
    }

    public StackoverflowQuestions getQuestion(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("questions/{id}")
                        .queryParam("order", "desc")
                        .queryParam("sort", "activity")
                        .queryParam("site", "stackoverflow")
                        .build(id))
                .retrieve().bodyToMono(StackoverflowQuestions.class)
                .timeout(Duration.ofMillis(10000))
                .block();
    }

    public StackoverflowAnswers getAnswers(String id, OffsetDateTime updatedAt) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("questions/{id}/answers")
                        .queryParam("fromdate", Date.from(updatedAt.toInstant()).getTime()/1000)
                        .queryParam("order", "desc")
                        .queryParam("sort", "activity")
                        .queryParam("site", "stackoverflow")
                        .build(id))
                .retrieve().bodyToMono(StackoverflowAnswers.class)
                .timeout(Duration.ofMillis(10000))
                .block();
    }
}
