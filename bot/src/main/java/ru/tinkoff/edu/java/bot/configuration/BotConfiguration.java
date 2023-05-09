package ru.tinkoff.edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    @Bean("telegramBot")
    public TelegramBot bot(@Value("${bot-token}") final String botToken) {
        return new TelegramBot(botToken);
    }
}
