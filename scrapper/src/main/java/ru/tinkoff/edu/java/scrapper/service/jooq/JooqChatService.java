package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;


@RequiredArgsConstructor
@Slf4j
public class JooqChatService implements ChatService {


    private final JooqChatRepository jooqChatRepository;

    @Override
    @Transactional
    public void register(long chatId) {
        if (jooqChatRepository.exist(chatId)) {
            log.info("chat {} exists", chatId);
            throw new ChatAlreadyExistsException();
        }
        jooqChatRepository.add(chatId);
        log.info("add chat {}", chatId);
    }

    @Override
    @Transactional
    public void unregister(long chatId) {
        if (!jooqChatRepository.exist(chatId)) {
            log.info("cant remove unregister user {}", chatId);
            throw new ChatNotFoundException();
        }
        jooqChatRepository.remove(chatId);
        log.info("delete chat {}", chatId);
    }
}
