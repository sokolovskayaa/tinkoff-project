package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.repository.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.Link;


import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.ChatLink.CHAT_LINK;
import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JooqLinkRepository {

    private final DSLContext dslContext;


    public List<Link> findAll(long chatId) {
        return dslContext.select().from(LINK.join(CHAT_LINK).on(CHAT_LINK.LINK_ID.eq(LINK.ID)))
                .where(CHAT_LINK.CHAT_ID.eq(chatId)).fetchInto(Link.class);
    }

    public Link add(long chat_id, String url) {
        Link link = addNewLink(url);
        if (exists(chat_id, url)) {
            return link;
        }
        log.info("add to chat link {} {}", chat_id, url);
        dslContext.insertInto(CHAT_LINK).values(chat_id, link.getId()).execute();
        return link;

    }

    public boolean exists(long chatId, String url) {
        return !dslContext.select().from(CHAT_LINK, LINK)
                .where(LINK.URL.eq(url).and(LINK.ID.eq(CHAT_LINK.LINK_ID).and(CHAT_LINK.CHAT_ID.eq(chatId)))).fetchInto(ChatLink.class)
                .isEmpty();
    }

    @Transactional
    public Link addNewLink(String url) {
        List<Link> links = dslContext.select().from(LINK).where(LINK.URL.eq(url)).fetchInto(Link.class);
        if (links.isEmpty()) {
            dslContext.insertInto(LINK).columns(LINK.URL).values(url).execute();
            links = dslContext.select().from(LINK).where(LINK.URL.eq(url)).fetchInto(Link.class);
        }
        return links.get(0);
    }

    public Link remove(long chat_id, String url) {
        Link link = getLink(url);
        dslContext.deleteFrom(CHAT_LINK).where(CHAT_LINK.CHAT_ID.eq(chat_id).and(CHAT_LINK.LINK_ID.eq((long) link.getId()))).execute();
        removeLastLink(link);
        return link;

    }

    public Link getLink(String url) {
        return dslContext.select()
                .from(CHAT_LINK, LINK)
                .where(LINK.URL.eq(url).and(LINK.ID.eq(CHAT_LINK.LINK_ID))).fetchOneInto(Link.class);
    }

    public Link removeLastLink(Link link) {
        List<Link> links = dslContext.select().from(CHAT_LINK, LINK)
                .where(LINK.URL.eq(link.getUrl()).and(LINK.ID.eq(CHAT_LINK.LINK_ID))).fetchInto(Link.class);
        if(links.isEmpty()) {
            dslContext.deleteFrom(LINK).where(LINK.ID.eq((long) link.getId()));
        }
        return link;
    }
}

