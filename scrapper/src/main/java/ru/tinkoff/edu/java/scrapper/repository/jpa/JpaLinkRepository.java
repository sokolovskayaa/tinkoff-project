package ru.tinkoff.edu.java.scrapper.repository.jpa;


import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import java.util.List;
@Repository
@Primary

public interface JpaLinkRepository extends JpaRepository<Link, Long> {
    List<Link> findAllByUrl(String url);
    @Query(value = "insert into link (url) values (?1)", nativeQuery = true)
    void addLink(String linkUrl);
    @Query(value = "select chat_id, link_id from chat_link, link where link.url = ?1 and link.id = link_id and chat_id = ?2", nativeQuery = true)
    List<ChatLink> getChatLinksByUrlAndChatId(long chatId, String LinkUrl);

    @Query(value = "INSERT INTO chat_link (chat_id, link_id) VALUES (?1, ?2)", nativeQuery = true)
    void addChatLink(long chatId, Link link);

    @Query(value = "DELETE FROM chat_link WHERE chat_id = ?1 and link_id = ?2", nativeQuery = true)
    void removeChatLink(ChatLink link);
    @Query(value = "select chat_id, link_id from chat_link, link where link.id = ?1 and link.id = link_id", nativeQuery = true)
    List<ChatLink> getChatLinksByLinkId(long linkId);
    @Transactional
    void removeLinkById(long linkId);
    @Query(value = "SELECT id, url, updated_at FROM link join chat_link on chat_link.link_id = link.id where chat_link.chat_id = ?1", nativeQuery = true)
    List<Link> findAllLinksInChat(long chatId);
}
