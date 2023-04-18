package ru.tinkoff.edu.java.bot.command;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.linkParser.parser.LinkParser;

public abstract class Command{

    protected final ScrapperClient scrapperClient = new ScrapperClient(); // @Component
    protected final LinkParser linkParser = new LinkParser();

    public abstract String command();

    public abstract String description();

    public abstract SendMessage handle(Update update);

    public abstract boolean supports(Update update);

    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}