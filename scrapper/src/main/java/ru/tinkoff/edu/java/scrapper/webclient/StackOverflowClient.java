package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.response.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.StackOverflowQuestionResponse;

public class StackOverflowClient {
    private final WebClient webClient;
    private final static String baseUrl = "https://api.stackexchange.com/";

    public StackOverflowClient() {
        webClient = WebClient.create(baseUrl);
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
