package ru.tinkoff.edu.java.bot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.BotService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BotController {

    private final BotService botService;

    @PostMapping(value = "/updates", consumes = APPLICATION_JSON_VALUE)
    public void updateLink(@RequestBody LinkUpdateRequest request)  {
        log.info("notify chats about update link {}", request.url());
        botService.updatelinks(request);
    }
}
