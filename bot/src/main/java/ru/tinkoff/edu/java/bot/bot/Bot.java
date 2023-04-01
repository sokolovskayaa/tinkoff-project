package ru.tinkoff.edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Slf4j
public class Bot {

    private final TelegramBot bot;
    private final UserMessageProcessor processor;

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(updates -> {
            for (var update : updates) {
                SendMessage s = processor.process(update);
                bot.execute(s);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }


}