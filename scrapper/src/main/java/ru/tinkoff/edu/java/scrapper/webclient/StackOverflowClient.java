package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.StackoverflowAnswers;
import ru.tinkoff.edu.java.scrapper.dto.response.StackoverflowQuestions;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Date;

@Component
public class StackOverflowClient {
    private static final String BASE_URL = "https://api.stackexchange.com/";
    private static final int SEC = 1000;
    private final WebClient webClient;

    public StackOverflowClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public StackOverflowClient(final String url) {
        webClient = WebClient.create(url);
    }

    public StackoverflowQuestions getQuestion(final String id) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("questions/{id}")
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("site", "stackoverflow")
                .build(id))
            .retrieve().bodyToMono(StackoverflowQuestions.class)
            .timeout(Duration.ofMillis(SEC))
            .block();
    }

    public StackoverflowAnswers getAnswers(final String id, final OffsetDateTime updatedAt) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("questions/{id}/answers")
                .queryParam("fromdate", Date.from(updatedAt.toInstant()).getTime() / SEC)
                .queryParam("order", "desc")
                .queryParam("sort", "activity")
                .queryParam("site", "stackoverflow")
                .build(id))
            .retrieve().bodyToMono(StackoverflowAnswers.class)
            .timeout(Duration.ofMillis(SEC))
            .block();
    }
}
