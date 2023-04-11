package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import static ru.tinkoff.edu.java.bot.enums.Command.HELP;

@Component
public class HelpCommand implements Command{
    @Override
    public String command() {
        return HELP.command;
    }

    @Override
    public String description() {
        return HELP.description;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        return messageText.equals(command());
    }
}