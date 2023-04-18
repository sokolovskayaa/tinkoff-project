package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.ChatLink;
import ru.tinkoff.edu.java.scrapper.dto.repository.Link;

import java.time.OffsetDateTime;
import java.util.List;

import static java.time.OffsetTime.now;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JdbcLinkUpdateRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Link> getLinks() {
        String sql = "select * from link where updated_at < ?"; // to check
        OffsetDateTime date = OffsetDateTime.now();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Link.class),  date.minusMinutes(1));
    }

    public void updateLink(Link link) {
        String sql = "update link set updated_at = now() where id = ?"; // to check
        jdbcTemplate.update(sql, link.getId());
    }

    public List<ChatLink> getChats(int linkId) {
        log.info("link id {}", linkId);
        String sql = "select * from chat_link, link where chat_link.link_id = link.id and link.id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ChatLink.class), linkId); // тут насчет класса в роу маппере хз
    }

}
