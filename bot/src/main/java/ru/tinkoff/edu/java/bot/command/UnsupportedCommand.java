package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static ru.tinkoff.edu.java.bot.enums.Command.UNSUPPORTED;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UnsupportedCommand implements Command {
    @Override
    public String command() {
        return UNSUPPORTED.command;
    }

    @Override
    public String description() {
        return UNSUPPORTED.description;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        return true;
    }
}
