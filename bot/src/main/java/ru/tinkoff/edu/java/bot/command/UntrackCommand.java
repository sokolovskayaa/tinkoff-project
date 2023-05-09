package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.request.RemoveLinkRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.tinkoff.edu.java.bot.enums.Command.UNTRACK;

@Component
@Slf4j
@RequiredArgsConstructor
public class UntrackCommand extends Command {
    private static final Pattern UNTRACK_REG = Pattern.compile(UNTRACK.command + " (.*)");
    private final ScrapperClient scrapperClient;

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
        String[] text = update.message().text().split(" ");
        log.info("User want to untrack link {}", text[1]);
        scrapperClient.deleteLink(chatId, new RemoveLinkRequest(text[1]));
        return new SendMessage(chatId, description());
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        Matcher matcher = UNTRACK_REG.matcher(messageText);
        return matcher.matches();
    }
}
