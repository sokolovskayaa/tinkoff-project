package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.linkParser.link.GitHubParsedLink;
import ru.tinkoff.edu.java.linkParser.link.StackOverflowParsedLink;
import ru.tinkoff.edu.java.linkParser.link.UnsupportedParsedLink;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.tinkoff.edu.java.bot.enums.Command.TRACK;

@Component
@Slf4j
public class TrackCommand extends Command {
    private final static Pattern TRACK_REG = Pattern.compile(TRACK.command + " (.*)");


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
        String link = update.message().text().split(" ")[1];
        if (!validLink(link)) {
            log.info("cant track {}", link);
            return new SendMessage(chatId, "I can track GitHub repositories and Stackoverflow questions only.");
        }
        log.info("track link {}", link);
        scrapperClient.addLink(chatId, new AddLinkRequest(link));
        return new SendMessage(chatId, description());
    }

    private boolean validLink(String link) {
        log.info("link hui? {}", link);
        var parsedLink = linkParser.parseLink(link);
        return (parsedLink instanceof GitHubParsedLink) || (parsedLink instanceof StackOverflowParsedLink);
    }

    @Override
    public boolean supports(Update update) {
        String messageText = update.message().text();
        Matcher matcher = TRACK_REG.matcher(messageText);
        return matcher.matches();
    }
}
