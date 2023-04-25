package ru.tinkoff.edu.java.scrapper.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.Commit;
import ru.tinkoff.edu.java.scrapper.dto.response.GitHubRepositoryResponse;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
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
        log.info(owner + " " + repo);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("repos/{owner}/{repo}").build(owner, repo))
                .header("Authorization", String.format("Bearer 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"))
                .retrieve()
                .bodyToMono(GitHubRepositoryResponse.class)
                .block();

    }

    public List<Commit> getCommits(String owner, String repo, OffsetDateTime updatedAt) {
        log.info(owner + " " + repo);
        return Arrays.stream(webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("repos/{owner}/{repo}/commits").queryParam("since", updatedAt).build(owner, repo))
                .header("Authorization", "Bearer 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")
                .retrieve()
                .bodyToMono(Commit[].class)
                .block()).toList();

    }
}
