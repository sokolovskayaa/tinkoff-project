package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.repository.hibernate.Link;

import java.util.List;

@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface JpaLinkRepository extends JpaRepository<Link, Long> {
    List<Link> findAllByUrl(String url);

    @Query(value = "insert into link (url) values (:url)", nativeQuery = true)
    @Modifying
    @Transactional
    void addLink(@Param("url") String linkUrl);

    @Transactional
    @Query(value = "select link_id from chat_link join link on link.id = chat_link.link_id where link.url = :url and chat_id = :chatId", nativeQuery = true)
    List<Long> getLinkIdByUrlAndChatId(@Param("chatId") long chatId, @Param("url") String LinkUrl);

    @Query(value = "INSERT INTO chat_link (chat_id, link_id) VALUES (:chatId, :linkId)", nativeQuery = true)
    @Modifying
    @Transactional
    void addChatLink(@Param("chatId") long chatId, @Param("linkId") long linkId);

    @Query(value = "DELETE FROM chat_link WHERE chat_id = :chatId and link_id = :linkId", nativeQuery = true)
    @Modifying
    @Transactional
    void removeChatLink(@Param("chatId") long chatId, @Param("linkId") long linkId);

    @Query(value = "select chat_id from chat_link, link where link.id = :linkId and link.id = link_id", nativeQuery = true)
    List<Long> getChatLinksByLinkId(@Param("linkId") long linkId);

    @Transactional
    void removeLinkById(long linkId);

    @Query(value = "SELECT id, url, updated_at FROM link join chat_link on chat_link.link_id = link.id where chat_link.chat_id = :chatId", nativeQuery = true)
    List<Link> findAllLinksInChat(@Param("chatId") long chatId);
}
