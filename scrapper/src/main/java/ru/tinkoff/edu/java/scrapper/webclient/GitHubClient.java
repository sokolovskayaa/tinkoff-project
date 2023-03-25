package ru.tinkoff.edu.java.scrapper.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.GitHubRepositoryResponse;

public class GitHubClient {

    private final WebClient webClient;
    
    private final String baseUrl = "https://api.github.com/";

    public GitHubClient() {
        webClient = WebClient.create(baseUrl);
    }

    public GitHubClient(String url) {
        webClient = WebClient.create(url);
    }

    public Mono<GitHubRepositoryResponse> getRepo(String owner, String repo) {
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                        .path("repos/{owner}/{repo}").build(owner, repo))
                        .retrieve().bodyToMono(GitHubRepositoryResponse.class);
    }
}
