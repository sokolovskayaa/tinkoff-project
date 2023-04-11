package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.response.GitHubRepositoryResponse;

public class GitHubClient {

    private final static String BASE_URL = "https://api.github.com/";
    private final WebClient webClient;


    public GitHubClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public GitHubClient(String url) {
        webClient = WebClient.create(url);
    }

    public GitHubRepositoryResponse getRepo(String owner, String repo) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("repos/{owner}/{repo}").build(owner, repo))
                .retrieve().bodyToMono(GitHubRepositoryResponse.class).block();
    }
}
