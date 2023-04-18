package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;


@Service
@RequiredArgsConstructor
@Slf4j
public class JdbcChatService implements ChatService {

    private final JdbcChatRepository jdbcChatRepository;

    @Override
    public void register(long chatId) {
        if (!jdbcChatRepository.exist(chatId)) {
            jdbcChatRepository.add(chatId);
            log.info("add chat {}", chatId);
        } else {
            log.info("chat {} exists", chatId);
            throw new ChatAlreadyExistsException();
        }

    }

    @Override
    public void unregister(long chatId) {
        if (!jdbcChatRepository.exist(chatId)) {
            log.info("cant remove unregister user {}", chatId);
            throw new ChatNotFoundException();
        } else {
            log.info("delete chat {}", chatId);
            jdbcChatRepository.remove(chatId);
        }
    }
}
