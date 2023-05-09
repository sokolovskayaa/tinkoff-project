package ru.tinkoff.edu.java.bot.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ru.tinkoff.edu.java.bot.command.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class BotTest {

    @Autowired
    private HelpCommand helpCommand;
    @Autowired
    private StartCommand startCommand;
    @Autowired
    private ListCommand listCommand;
    @Autowired
    private TrackCommand trackCommand;
    @Autowired
    private UnsupportedCommand unsupportedCommand;
    @Autowired
    private UntrackCommand untrackCommand;



    @Test
    void ListOfTrackedLinksIsEmpty() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", 1L);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(message, "text", "/list");
        ReflectionTestUtils.setField(update, "message", message);
        Assertions.assertAll(
                () -> Assertions.assertTrue(listCommand.supports(update)),
                () -> Assertions.assertFalse(helpCommand.supports(update)),
                () -> Assertions.assertFalse(startCommand.supports(update)),
                () -> Assertions.assertFalse(trackCommand.supports(update)),
                () -> Assertions.assertFalse(untrackCommand.supports(update))
        );
    }

    @Test
    void UnsupportedCommand() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", 1L);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(message, "text", "/show");
        ReflectionTestUtils.setField(update, "message", message);
        Assertions.assertAll(
                () -> Assertions.assertTrue(unsupportedCommand.supports(update)),
                () -> Assertions.assertFalse(helpCommand.supports(update)),
                () -> Assertions.assertFalse(startCommand.supports(update)),
                () -> Assertions.assertFalse(trackCommand.supports(update)),
                () -> Assertions.assertFalse(untrackCommand.supports(update)),
                () -> Assertions.assertFalse(listCommand.supports(update)),
                () -> Assertions.assertEquals("unsupported command", unsupportedCommand.handle(update).getParameters().get("text"))
        );
    }

    @Test
    void TrackCommandTest() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        ReflectionTestUtils.setField(chat, "id", 1L);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(message, "text", "/track https://github.com/sokolovskayaa/tinkoff-project");
        ReflectionTestUtils.setField(update, "message", message);
        Assertions.assertAll(
                () -> Assertions.assertFalse(listCommand.supports(update)),
                () -> Assertions.assertFalse(helpCommand.supports(update)),
                () -> Assertions.assertFalse(startCommand.supports(update)),
                () -> Assertions.assertTrue(trackCommand.supports(update)),
                () -> Assertions.assertFalse(untrackCommand.supports(update))
        );
    }
}
