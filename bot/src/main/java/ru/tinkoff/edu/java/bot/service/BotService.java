package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.bot.Bot;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotService {
    private final Bot bot;

    public void updatelinks(LinkUpdateRequest request){
        log.info("bot service up!!1");
        for(var chatId : request.tgChatIds()) {
            log.info("notify chat {} about link {}", chatId, request.url());
            bot.sendMessage(chatId, request.url());
        }
    }
}
