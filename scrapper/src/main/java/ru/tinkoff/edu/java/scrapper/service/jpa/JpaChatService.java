package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Chat;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;


@RequiredArgsConstructor
@Slf4j
public class JpaChatService implements ChatService {

    private final JpaChatRepository jdbcChatRepository;

    @Override
    public void register(long chatId) {
        if (jdbcChatRepository.existsById(chatId)) {
            log.info("chat {} exists", chatId);
            throw new ChatAlreadyExistsException();
        }
        Chat chat = new Chat();
        chat.setId(chatId);
        jdbcChatRepository.save(chat);
        log.info("add chat {}", chatId);
    }

    @Override
    public void unregister(long chatId) {
        if (!jdbcChatRepository.existsById(chatId)) {
            log.info("cant remove unregister user {}", chatId);
            throw new ChatNotFoundException();
        }
        Chat chat = new Chat();
        chat.setId(chatId);
        jdbcChatRepository.delete(chat);
        log.info("delete chat {}", chatId);
    }
}
