package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ScrapperController {

    @PostMapping(value = "/tg-chat/{id}", produces = APPLICATION_JSON_VALUE)
    public boolean addChat(@PathVariable("id") long id) {
        return true;
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public boolean deleteChat(@PathVariable("id") long id) {
        return true;
    }

    @GetMapping(value = "/links", produces = APPLICATION_JSON_VALUE)
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") long id) {
        return null;
    }

    @PostMapping(value = "/links", consumes = APPLICATION_JSON_VALUE)
    public boolean addLink(@RequestHeader("Tg-Chat-Id") long id, @RequestBody AddLinkRequest request) {
        return true;
    }

    @DeleteMapping(value = "/links", consumes = APPLICATION_JSON_VALUE)
    public boolean deleteLink(@RequestHeader("Tg-Chat-Id") long id, @RequestBody RemoveLinkRequest request) {
        return true;
    }
}
