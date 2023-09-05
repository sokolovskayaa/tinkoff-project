package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.bot.Bot;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;

@RabbitListener(queues = "${app.queue-properties.queue}")
@Slf4j
@RequiredArgsConstructor
@Service
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class ScrapperQueueListener implements Receiver {
    private final Bot bot;

    @RabbitHandler
    public void updateLinks(LinkUpdateRequest request) {
        for (var chatId : request.tgChatIds()) {
            log.info("notify chat {} about link {}", chatId, request.url());
            bot.sendMessage(chatId, request.url());
        }
    }
}
