package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;

import static ru.tinkoff.edu.java.bot.enums.Command.HELP;

@Component
@RequiredArgsConstructor
public class HelpCommand extends Command {

    private final ScrapperClient scrapperClient;

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
