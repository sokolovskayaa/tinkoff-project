package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.Chat;

import java.sql.PreparedStatement;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat.CHAT;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JooqChatRepository {

    private final DSLContext dslContext;

    public void add(long chatId) {
        dslContext.insertInto(CHAT).values(chatId).execute();
        log.info("chat {chatId} shoud be addedd");
    }

    public void remove(long chatId) {
        dslContext.deleteFrom(CHAT).where(CHAT.ID.eq(chatId)).execute();
    }

    public List<Chat> findAll() {
        return dslContext.selectFrom(CHAT).fetchInto(Chat.class);
    }

    public boolean exist(long chatId) {
        var chats = dslContext.selectFrom(CHAT).where(CHAT.ID.eq(chatId)).fetchInto(Chat.class);
        return !chats.isEmpty();
    }
}

