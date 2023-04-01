package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import static ru.tinkoff.edu.java.bot.enums.Command.EMPTY_LIST;
import static ru.tinkoff.edu.java.bot.enums.Command.LIST;

@Component
public class ListCommand implements Command {
    @Override
    public String command() {
        return LIST.command;
    }

    @Override
    public String description() {
        return LIST.description;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        if (false) {  // тут потом должен быть лист урлов, но пока их нет
            return new SendMessage(chatId, description());
        }
        return new SendMessage(chatId, EMPTY_LIST.description);
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        return messageText.equals(command());
    }
}
