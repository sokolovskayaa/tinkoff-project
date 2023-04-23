package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
@RequiredArgsConstructor
public class UnsupportedCommand extends Command {
    private static final String UNSUPPORTED_COMMAND = "unsupported command";
    private final ScrapperClient scrapperClient;

    @Override
    public String command() {
        return null;
    }

    @Override
    public String description() {
        return UNSUPPORTED_COMMAND;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        log.info(chatId + " " + description());
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        return true;
    }
}
