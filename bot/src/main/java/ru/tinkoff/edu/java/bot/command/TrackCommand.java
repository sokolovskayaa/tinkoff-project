package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.tinkoff.edu.java.bot.enums.Command.TRACK;

@Component
public class TrackCommand implements Command{
    private final static Pattern TRACK_REG = Pattern.compile(TRACK.command + " (\\w*)");

    @Override
    public String command() {
        return TRACK.command;
    }

    @Override
    public String description() {
        return TRACK.description;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        Matcher matcher = TRACK_REG.matcher(messageText); // тут надо будет проверять на валидность ссылки
        return matcher.matches();
    }
}
