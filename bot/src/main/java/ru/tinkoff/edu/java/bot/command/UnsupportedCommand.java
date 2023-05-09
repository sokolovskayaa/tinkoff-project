package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
@RequiredArgsConstructor
public class UnsupportedCommand extends Command {
    private static final String UNSUPPORTED_COMMAND = "unsupported command";

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
        log.info("User's command is unsupported");
        Long chatId = update.message().chat().id();
        log.info(chatId + " " + description());
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        return true;
    }
}
