package ru.tinkoff.edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
@Slf4j
public class Bot {

    private final TelegramBot bot;
    private final UserMessageProcessor processor;

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(updates -> {
            for(var update : updates) {
                SendMessage s = processor.process(update);
                log.info("send message?");
//                try {

                    bot.execute(s);
//                } catch (NullPointerException e){
//                    log.error("Fuck it");
//                    e.getMessage();
//                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }


}