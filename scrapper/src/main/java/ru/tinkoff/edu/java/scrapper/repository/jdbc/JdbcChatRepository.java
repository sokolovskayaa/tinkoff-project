package ru.tinkoff.edu.java.scrapper.repository.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.repository.jdbc.Chat;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcChatRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_CHAT_QUERY = "INSERT INTO chat (id) VALUES (?)";
    private static final String DELETE_CHAT_QUERY = "DELETE FROM chat WHERE id = ?";
    private static final String FIND_ALL_CHATS_QUERY = "SELECT * FROM chat";
    private static final String CHAT_EXISTS_QUERY = "select * from chat where id = ?";

    public void add(long chatId) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_CHAT_QUERY);
            ps.setLong(1, chatId);
            return ps;
        });
    }

    public void remove(long chatId) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(DELETE_CHAT_QUERY);
            ps.setLong(1, chatId);
            return ps;
        });
    }
    public List<Chat> findAll() {
        return jdbcTemplate.query(FIND_ALL_CHATS_QUERY, (rs, rowNum) -> {
            Chat chat = new Chat();
            chat.setId(rs.getLong("id"));
            return chat;
        });
    }

    public boolean exist(long chatId) {
        var chats = jdbcTemplate.query(CHAT_EXISTS_QUERY, new BeanPropertyRowMapper<>(Chat.class), chatId);
        return !chats.isEmpty();
    }
}

