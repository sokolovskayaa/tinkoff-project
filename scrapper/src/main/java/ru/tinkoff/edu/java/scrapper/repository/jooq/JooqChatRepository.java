package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.Chat;

import java.sql.PreparedStatement;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat.CHAT;

@RequiredArgsConstructor
@Slf4j
@Repository
public class JooqChatRepository {

    private final DSLContext dslContext;

    public void add(long chatId) {
        dslContext.insertInto(CHAT).values(chatId).execute();
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

