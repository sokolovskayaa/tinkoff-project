package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.ChatLink;
import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos.Link;

import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.ChatLink.CHAT_LINK;
import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;

@RequiredArgsConstructor
@Slf4j
public class JooqLinkRepository {

    private final DSLContext dslContext;


    public List<Link> findAllLinksInChat(long chatId) {
        return dslContext.select().from(LINK.join(CHAT_LINK).on(CHAT_LINK.LINK_ID.eq(LINK.ID)))
                .where(CHAT_LINK.CHAT_ID.eq(chatId)).fetchInto(Link.class);
    }

    public void addChatLink(long chatId, Link link) {
        dslContext.insertInto(CHAT_LINK).values(chatId, link.getId()).execute();
    }

    public List<Link> getLinksFromLinkByUrl(String url) {
        return dslContext.selectFrom(LINK).where(LINK.URL.eq(url)).fetchInto(Link.class);
    }

    public List<ChatLink> getChatLinksByUrlAndChatId(long chatId, String url) {
        return dslContext.select().from(CHAT_LINK, LINK).where(LINK.URL.eq(url)
                .and(LINK.ID.eq(CHAT_LINK.LINK_ID))
                .and(CHAT_LINK.CHAT_ID.eq(chatId))).fetchInto(ChatLink.class);
    }

    public List<ChatLink> getChatLinksByLinkId(long linkId) {
        return dslContext.select().from(CHAT_LINK, LINK).where(LINK.ID.eq(linkId)
                .and(LINK.ID.eq(CHAT_LINK.LINK_ID))).fetchInto(ChatLink.class);
    }

    public void addLink(String url) {
        dslContext.insertInto(LINK).columns(LINK.URL).values(url).execute();
    }

    public void removeChatLink(ChatLink link) {
        dslContext.deleteFrom(CHAT_LINK).where(CHAT_LINK.CHAT_ID.eq(link.getChatId()).and(CHAT_LINK.LINK_ID.eq((long) link.getLinkId()))).execute();
    }

    public void removeLastLink(long linkId) {
        dslContext.deleteFrom(LINK).where(LINK.ID.eq(linkId));
    }
}

