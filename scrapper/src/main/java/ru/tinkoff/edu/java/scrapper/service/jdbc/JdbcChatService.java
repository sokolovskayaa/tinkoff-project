package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;


@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class JdbcChatService implements ChatService {

    private final JdbcChatRepository jdbcChatRepository;

    @Override
    @Transactional
    public void register(long chatId) {
        if (jdbcChatRepository.exist(chatId)) {
            log.info("chat {} exists", chatId);
            throw new ChatAlreadyExistsException();
        }
        jdbcChatRepository.add(chatId);
        log.info("add chat {}", chatId);
    }

    @Override
    @Transactional
    public void unregister(long chatId) {
        if (!jdbcChatRepository.exist(chatId)) {
            log.info("cant remove unregister user {}", chatId);
            throw new ChatNotFoundException();
        }
        jdbcChatRepository.remove(chatId);
        log.info("delete chat {}", chatId);
    }
}