package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.response.StackOverflowQuestionResponse;

public class StackOverflowClient {
    private final static String BASE_URL = "https://api.stackexchange.com/";
    private final WebClient webClient;

    public StackOverflowClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public StackOverflowClient(String url) {
        webClient = WebClient.create(url);
    }

    public Mono<StackOverflowQuestionResponse> getRepo(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("questions/{id}").build(id))
                .retrieve().bodyToMono(StackOverflowQuestionResponse.class);
    }
}
