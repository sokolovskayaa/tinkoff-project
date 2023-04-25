package ru.tinkoff.edu.java.scrapper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.service.ChatService;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ScrapperController {

    private final ChatService chatService;
    private final LinkService linkService;

    @PostMapping(value = "/tg-chat/{id}")
    public void addChat(@PathVariable("id") long id) {
        log.info("add chat {}", id);
        chatService.register(id);
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public void deleteChat(@PathVariable("id") long id) {
        log.info("delete chat {}", id);
        chatService.unregister(id);
    }

    @GetMapping(value = "/links", produces = APPLICATION_JSON_VALUE)
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") long id) {
        log.info("show list of links {}", id);
        List<LinkResponse> links = new ArrayList<>();
        for (var i : linkService.listAll(id)) {
            links.add(new LinkResponse(i.getId(), i.getUrl()));
        }
        return new ListLinksResponse(links, links.size());
    }

    @PostMapping(value = "/links", consumes = APPLICATION_JSON_VALUE)
    public void addLink(@RequestHeader("Tg-Chat-Id") long id, @RequestBody AddLinkRequest request) {
        log.info("add link {} to chat {}", request.link(), id);
        linkService.add(id, URI.create(request.link()));
    }

    @DeleteMapping(value = "/links", consumes = APPLICATION_JSON_VALUE)
    public void deleteLink(@RequestHeader("Tg-Chat-Id") long id, @RequestBody RemoveLinkRequest request) {
        log.info("delete link {} from chat {}", request.url(), id);
        linkService.remove(id, URI.create(request.url()));
    }
}
