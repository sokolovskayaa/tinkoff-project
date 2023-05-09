package ru.tinkoff.edu.java.scrapper.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.dto.response.Commit;
import ru.tinkoff.edu.java.scrapper.dto.response.GitHubRepositoryResponse;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class GitHubClient {

    private static final String BASE_URL = "https://api.github.com/";

    @Value("${github-token}")
    private String githubToken;
    private final WebClient webClient;

    public GitHubClient() {
        webClient = WebClient.create(BASE_URL);
    }

    public GitHubClient(final String url) {
        webClient = WebClient.create(url);
    }

    public GitHubRepositoryResponse getRepo(final String owner, final String repo) {
        log.info(owner + " " + repo);
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("repos/{owner}/{repo}").build(owner, repo))
            .header("Authorization", String.format("Bearer %s", githubToken))
            .retrieve()
            .bodyToMono(GitHubRepositoryResponse.class)
            .block();

    }

    public List<Commit> getCommits(final String owner, final String repo, final OffsetDateTime updatedAt) {
        log.info("Get commits from {}/{} since {}", owner, repo, updatedAt);
        return Arrays.stream(Objects.requireNonNull(webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("repos/{owner}/{repo}/commits").queryParam("since", updatedAt).build(owner, repo))
            .header("Authorization", String.format("Bearer %s", githubToken))
            .retrieve()
            .bodyToMono(Commit[].class)
            .block())).toList();
    }
}
