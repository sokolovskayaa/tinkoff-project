package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;

import static ru.tinkoff.edu.java.bot.enums.Command.LIST;

@Component
@Slf4j
public class ListCommand extends Command {
    private static final String EMPTY_LIST = "no tracked links";
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
        ListLinksResponse response = scrapperClient.getLinks(chatId);
        log.info("show list {}", chatId);
        if (response.links().isEmpty()) {
            log.info(chatId + " " + EMPTY_LIST);
            return new SendMessage(chatId, EMPTY_LIST);
        }
        return new SendMessage(chatId, response.toString());

    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        return messageText.equals(command());
    }
}
