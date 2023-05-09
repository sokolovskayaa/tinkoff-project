package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static ru.tinkoff.edu.java.bot.enums.Command.HELP;

@Component
@Slf4j
public class HelpCommand extends Command {

    @Override
    public String command() {
        return HELP.command;
    }

    @Override
    public String description() {
        return HELP.description;
    }

    @Override
    public SendMessage handle(final Update update) {
        log.info("User ask for help");
        Long chatId = update.message().chat().id();
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(final Update update) {
        String messageText = update.message().text();
        return messageText.equals(command());
    }
}
