package ru.tinkoff.edu.java.bot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.Receiver;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BotController {

    private final Receiver receiver;

    @PostMapping(value = "/updates", consumes = APPLICATION_JSON_VALUE)
    public void updateLink(@RequestBody final LinkUpdateRequest request) {
        log.info("notify chats about update link {}", request.url());
        receiver.updateLinks(request);
    }
}
