package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.Link;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JdbcLinkUpdateRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT_OLD_LINKS_QUERY = "select * from link where updated_at < ?";
    private static final String UPDATE_LINK_QUERY = "update link set updated_at = now() where id = ?";
    private static final String SELECT_CHATS_BY_LINK_QUERY = "select * from chat_link, link where chat_link.link_id = link.id and link.id = ?";

    public List<Link> getLinks() {
        OffsetDateTime date = OffsetDateTime.now();
        return jdbcTemplate.query(SELECT_OLD_LINKS_QUERY,
                new BeanPropertyRowMapper<>(Link.class),
                date.minusMinutes(1));
    }

    public void updateLink(Link link) {
        jdbcTemplate.update(UPDATE_LINK_QUERY, link.getId());
    }

    public List<ChatLink> getChats(int linkId) {
        return jdbcTemplate.query(SELECT_CHATS_BY_LINK_QUERY, new BeanPropertyRowMapper<>(ChatLink.class), linkId);
    }

}
