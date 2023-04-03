package ru.tinkoff.edu.java.bot.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import ru.tinkoff.edu.java.bot.command.*;

public class BotTest {

    @Test
    public void ListOfTrackedLinksIsEmpty() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", 1L);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(message, "text", "/list");
        ReflectionTestUtils.setField(update, "message", message);
        Assertions.assertAll(
                () -> Assertions.assertTrue(new ListCommand().supports(update)),
                () -> Assertions.assertFalse(new HelpCommand().supports(update)),
                () -> Assertions.assertFalse(new StartCommand().supports(update)),
                () -> Assertions.assertFalse(new TrackCommand().supports(update)),
                () -> Assertions.assertFalse(new UntrackCommand().supports(update)),
                () -> Assertions.assertEquals(new ListCommand().handle(update).getParameters().get("text"), "no tracked links")
        );
    }

    @Test
    public void UnsupportedCommand() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", 1L);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(message, "text", "/show");
        ReflectionTestUtils.setField(update, "message", message);
        Assertions.assertAll(
                () -> Assertions.assertTrue(new UnsupportedCommand().supports(update)),
                () -> Assertions.assertFalse(new HelpCommand().supports(update)),
                () -> Assertions.assertFalse(new StartCommand().supports(update)),
                () -> Assertions.assertFalse(new TrackCommand().supports(update)),
                () -> Assertions.assertFalse(new UntrackCommand().supports(update)),
                () -> Assertions.assertFalse(new ListCommand().supports(update)),
                () -> Assertions.assertEquals(new UnsupportedCommand().handle(update).getParameters().get("text"), "unsupported command")
        );
    }
}
