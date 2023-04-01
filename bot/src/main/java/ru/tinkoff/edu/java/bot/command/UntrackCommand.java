package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.tinkoff.edu.java.bot.enums.Command.UNTRACK;

@Component
public class UntrackCommand implements Command {
    private final static Pattern UNTRACK_REG = Pattern.compile("/untrack (\\w*)");

    @Override
    public String command() {
        return UNTRACK.command;
    }

    @Override
    public String description() {
        return UNTRACK.description;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        Matcher matcher = UNTRACK_REG.matcher(messageText); // тут надо будет проверять на валидность ссылки
        return matcher.matches();
    }
}
