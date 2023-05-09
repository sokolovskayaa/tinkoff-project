package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.bot.Bot;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@RequiredArgsConstructor
@Slf4j
@Service
@ConditionalOnProperty(prefix = "app",
                       name = "use-queue",
                       havingValue = "false")
public class BotService implements Receiver {
    private final Bot bot;

    public void updateLinks(final LinkUpdateRequest request) {
        for (var chatId : request.tgChatIds()) {
            log.info("notify chat {} about link {}", chatId, request.url());
            bot.sendMessage(chatId, request.url());
        }
    }
}
