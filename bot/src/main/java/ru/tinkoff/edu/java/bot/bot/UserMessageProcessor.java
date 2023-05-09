package ru.tinkoff.edu.java.bot.bot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.command.Command;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserMessageProcessor {
    private final List<Command> commands;

    public SendMessage process(final Update update) {
        for (var command : commands) {
            if (command.supports(update)) {
                try {
                    return command.handle(update);
                } catch (Exception e) {
                    return new SendMessage(
                        update.message().chat().id(),
                        "Произошла непредвиденная ошибка: " + e.getMessage()
                    );
                }
            }
        }
        return null;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
