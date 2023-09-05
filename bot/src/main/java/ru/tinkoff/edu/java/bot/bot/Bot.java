package ru.tinkoff.edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.command.Command;

@RequiredArgsConstructor
@Component
@Slf4j
public class Bot {
    private final TelegramBot telegramBot;
    private final UserMessageProcessor processor;

    @PostConstruct
    public void init() {
        initMenu();
        telegramBot.setUpdatesListener(updates -> {
            for (var update : updates) {
                log.info(update.message().text());
                SendMessage s = processor.process(update);
                telegramBot.execute(s);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public void initMenu() {
        telegramBot.execute(new SetMyCommands(processor.getCommands().stream()
            .map(Command::toApiCommand).toArray(BotCommand[]::new)));
    }

    public void sendMessage(final long chatId, final String url) {
        log.info("notify chat {} about link {}", chatId, url);
        String text = String.format("Your link %s has been updated", url);
        SendMessage request = new SendMessage(chatId, text);
        telegramBot.execute(request);
    }

}
