package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static ru.tinkoff.edu.java.bot.enums.Command.START;

@Component
@Slf4j
public class StartCommand extends Command {
    @Override
    public String command() {
        return START.command;
    }

    @Override
    public String description() {
        return START.description;
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.message().chat().id();
        log.info("add chat {}", chatId);
        scrapperClient.addChat(chatId);
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        return messageText.equals(command());
    }
}
