package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.Link;

import java.sql.PreparedStatement;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcLinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Link> findAll(long chatId) { // все линки в чате
        String sql = "SELECT id, url, updated_at FROM link join chat_link on chat_link.link_id = link.id where chat_link.chat_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Link.class), chatId); // prepared statemant нада
    }

    public Link add(long chat_id, String url) {
        Link link = addNewLink(url);
        if(exists(chat_id, url)) {
            return link;
        }
        log.info("add to chat link {} {}", chat_id, url);
        String sql = "INSERT INTO chat_link (chat_id, link_id) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, chat_id);
            ps.setInt(2, link.getId());
            return ps;
        });
        return link;
    }

    public boolean exists(long chatId, String url) {
        String sql = "select * from chat_link, link where link.url = ? and link.id = link_id and chat_id = ?";
        if(jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChatLink.class), url, chatId).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public Link addNewLink(String url) {
        String sql = "select * from link where url = ?";
        List<Link> links = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Link.class), url);
        if (links.size() == 0) { // если там не 0/1, значит какая-то пизда, по одному урлу одна линка должна быть, и чо делать?
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("insert into link (url) values (?)");
                ps.setString(1, url);
                return ps;
            });
            links = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Link.class), url);
        }
        return links.get(0);
    }

    public Link remove(long chat_id, String url) { // убрать из чата и линк??
        Link link = getLink(url); // вернуть линку
        String sql = "DELETE FROM chat_link WHERE chat_id = ? and link_id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, chat_id);
            ps.setInt(2, link.getId());
            return ps;
        });
        removeLastLink(link); // убрать из линк если надо
        return link;
    }

    public Link getLink(String url)  {
        String sql = "select link.id, link.url, link.updated_at from chat_link, link where link.url = ? and link.id = chat_link.link_id;";
        List<Link> links = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Link.class), url);
        return links.get(0);
    }

    public Link removeLastLink(Link link)  {
        String sql = "select link.id, link.url, link.updated_at from chat_link, link where link.url = ? and link.id = chat_link.link_id;";
        List<Link> links = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Link.class), link.getUrl());
        if (links.size() == 0) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("delete from link where link.id = ?");
                ps.setInt(1, link.getId());
                return ps;
            });
        }
        return link;
    }
}
