package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.Link;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.ChatLink.CHAT_LINK;
import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat.CHAT;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JooqLinkUpdateRepository {

    private final DSLContext dslContext;

    public List<Link> getLinks() {
        OffsetDateTime curDate = OffsetDateTime.now().minusMinutes(1);
        return dslContext.select().from(LINK)
                .where(LINK.UPDATED_AT.lessThan(curDate)).fetchInto(Link.class);
    }

    public void updateLink(Link link) {
        dslContext.update(LINK)
                .set(LINK.UPDATED_AT, OffsetDateTime.now())
                .where(LINK.ID.eq((long) link.getId())).execute();
    }

    public List<ChatLink> getChats(long linkId) {
        log.info("link id {}", linkId);
        return dslContext.select()
                .from(CHAT_LINK, LINK).where(CHAT_LINK.LINK_ID.eq(LINK.ID).and(LINK.ID.eq(linkId))).fetchInto(ChatLink.class);
    }
}

