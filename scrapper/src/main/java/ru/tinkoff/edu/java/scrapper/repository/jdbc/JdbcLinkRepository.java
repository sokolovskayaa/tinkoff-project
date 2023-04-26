package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.Link;

import java.sql.PreparedStatement;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Repository
//@Primary

public class JdbcLinkRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT_LINKS_FROM_CHAT_QUERY = "SELECT id, url, updated_at FROM link join chat_link on chat_link.link_id = link.id where chat_link.chat_id = ?";
    private static final String INSERT_CHAT_LINK_QUERY = "INSERT INTO chat_link (chat_id, link_id) VALUES (?, ?)";
    private static final String SELECT_LINKS_FROM_LINK_BY_URL_QUERY = "select * from link where url = ?";
    private static final String SELECT_LINKS_FROM_LINK_BY_ID_QUERY = "select * from link where id = ?";
    private static final String SELECT_CHAT_LINK_BY_ID_AND_URL_QUERY = "select chat_id, link_id from chat_link, link where link.url = ? and link.id = link_id and chat_id = ?";
    private static final String SELECT_CHAT_LINK_BY_LINK_ID = "select chat_id, link_id from chat_link, link where link.id = ? and link.id = link_id";
    private static final String INSERT_LINK_QUERY = "insert into link (url) values (?)";
    private static final String DELETE_CHAT_LINK_QUERY = "DELETE FROM chat_link WHERE chat_id = ? and link_id = ?";
    private static final String DELETE_LINK_QUERY = "delete from link where link.id = ?";
    private static final String SELECT_CHAT_LINK_BY_URL_QUERY = "select link.id, link.url, link.updated_at from chat_link, link where link.url = ? and link.id = chat_link.link_id;";


    public List<Link> findAllLinksInChat(long chatId) {
        return jdbcTemplate.query(SELECT_LINKS_FROM_CHAT_QUERY, new BeanPropertyRowMapper<>(Link.class), chatId);
    }

    public void addChatLink(long chatId, Link link) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_CHAT_LINK_QUERY);
            ps.setLong(1, chatId);
            ps.setInt(2, link.getId());
            return ps;
        });
    }

    public List<Link> getLinksFromLinkByUrl(String url) {
        return jdbcTemplate.query(SELECT_LINKS_FROM_LINK_BY_URL_QUERY, new BeanPropertyRowMapper<>(Link.class), url);
    }

    public List<ChatLink> getChatLinksByUrlAndChatId(long chatId, String url) {
        return jdbcTemplate.query(SELECT_CHAT_LINK_BY_ID_AND_URL_QUERY, new BeanPropertyRowMapper(ChatLink.class), url, chatId);
    }
    public List<ChatLink> getChatLinksByLinkId(long linkId) {
        return jdbcTemplate.query(SELECT_CHAT_LINK_BY_LINK_ID, new BeanPropertyRowMapper(ChatLink.class),linkId);
    }

    public void addLink(String url) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_LINK_QUERY);
            ps.setString(1, url);
            return ps;
        });
    }


    public void removeChatLink(ChatLink link) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(DELETE_CHAT_LINK_QUERY);
            ps.setLong(1, link.getChatId());
            ps.setInt(2, link.getLinkId());
            return ps;
        });
    }

    public void removeLastLink(long linkId) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(DELETE_LINK_QUERY);
            ps.setInt(1, (int) linkId);
            return ps;
        });
    }
}
