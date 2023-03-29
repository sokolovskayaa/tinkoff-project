package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.GitHubRepositoryResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.webclient.GitHubClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ScrapperController {

    @PostMapping(value = "/tg-chat/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<GitHubRepositoryResponse> addChat(@PathVariable ("id") long id) {
        Mono<GitHubRepositoryResponse> a = null;
        try {
            a = (new GitHubClient()).getRepo("sokolovskayaa", "tinkoff-project");
        } catch (Exception e){
            e.getMessage();
        }

        return a;
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public String deleteChat(@PathVariable ("id") long id) {
        return "ok";
    }

    @GetMapping(value = "/links", produces = APPLICATION_JSON_VALUE)
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") long id) {
        return null;
    }

    @PostMapping(value = "/links", consumes = APPLICATION_JSON_VALUE)
    public String addLink(@RequestHeader("Tg-Chat-Id") long id, @RequestBody AddLinkRequest request) {
        return "ok";
    }

    @DeleteMapping(value = "/links", consumes = APPLICATION_JSON_VALUE)
    public String deleteLink(@RequestHeader ("Tg-Chat-Id") long id, @RequestBody RemoveLinkRequest request) {
        return "ok";
    }
}
