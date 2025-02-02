package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoriteDao implements FavoriteDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcFavoriteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFavorite(int userId, int eventId) {
        String sql = "INSERT INTO favorites (user_id, event_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, userId, eventId);
    }

    @Override
    public void removeFavorite(int userId, int eventId) {
        String sql = "DELETE FROM favorites WHERE user_id = ? AND event_id = ?";
        jdbcTemplate.update(sql, userId, eventId);
    }

    @Override
    public List<Integer> getFavoriteEventIds(int userId) {
        List<Integer> eventIds = new ArrayList<>();
        String sql = "SELECT event_id FROM favorites WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            eventIds.add(results.getInt("event_id"));
        }
        return eventIds;
    }
}

