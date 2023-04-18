package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.Chat;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcChatRepository {

    private final JdbcTemplate jdbcTemplate;

    public void add(long chatId) {
        String sql = "INSERT INTO chat (id) VALUES (?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, chatId);
            return ps;
        });
        log.info("chat {chatId} shoud be addedd");
    }

    public void remove(long chatId) {
        String sql = "DELETE FROM chat WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, chatId);
            return ps;
        });
    }
    public List<Chat> findAll() {
        String sql = "SELECT * FROM chat";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Chat chat = new Chat();
            chat.setId(rs.getLong("id"));
            return chat;
        });
    }

    public boolean exist(long chatId) {
        var chats = jdbcTemplate.query("select * from chat where id = ?", new BeanPropertyRowMapper<>(Chat.class), chatId);
        return !chats.isEmpty();
    }
}

