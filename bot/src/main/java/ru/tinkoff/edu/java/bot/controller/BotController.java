package ru.tinkoff.edu.java.bot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BotController {

    @PostMapping(value = "/updates", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public LinkUpdateRequest updateLink(@RequestBody LinkUpdateRequest linkUpdateRequest)  {
        return null;
    }
}
