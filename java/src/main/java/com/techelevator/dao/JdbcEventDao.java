package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getAllApprovedEvents(Integer userId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE status = 'approved'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Event event = mapRowToEvent(results);
            event.setEventDates(getEventDates(event.getEventId()));
            // Optionally, if userId is provided, you might mark favorites.
            if (userId != null && isEventFavorited(userId, event.getEventId())) {
                event.setFavorited(true);
            }
            events.add(event);
        }
        return events;
    }


    @Override
    public boolean isEventFavorited(int userId, int eventId) {
        String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND event_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, eventId);
        return count != null && count > 0;
    }

    @Override
    public List<Event> getUserSubmissions(int userId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE created_by = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Event event = mapRowToEvent(results);
            // Retrieve and set event dates for this event.
            event.setEventDates(getEventDates(event.getEventId()));
            events.add(event);
        }
        return events;
    }

    @Override
    public void submitEvent(Event event) {
        // Safely join masking policy and accessibility options,
        // using an empty string if the list is null.
        String maskingPolicy = event.getMaskingPolicy() != null
                ? String.join(",", event.getMaskingPolicy())
                : "";
        String accessibilityOptions = event.getAccessibilityOptions() != null
                ? String.join(",", event.getAccessibilityOptions())
                : "";

        String sql = "INSERT INTO events (title, description, company_name, venue_name, venue_address, ticket_price, event_link, email_address, content_warnings, image_url, status, masking_policy, accessibility_options, other_accessibility, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'pending', ?, ?, ?, ?) RETURNING event_id";

        int eventId = jdbcTemplate.queryForObject(
                sql,
                int.class,
                event.getTitle(),
                event.getDescription(),
                event.getCompanyName(),
                event.getVenueName(),
                event.getVenueAddress(),
                event.getTicketPrice(),
                event.getEventLink(),
                event.getEmailAddress(),
                event.getContentWarnings(),
                event.getImageUrl(),  // May be null if not provided.
                maskingPolicy,
                accessibilityOptions,
                event.getOtherAccessibility(),
                event.getCreatedBy()
        );

        // Insert event dates using direct insertion of LocalDateTime
        if (event.getEventDates() != null && !event.getEventDates().isEmpty()) {
            for (LocalDateTime date : event.getEventDates()) {
                jdbcTemplate.update(
                        "INSERT INTO event_dates (event_id, event_date) VALUES (?, ?)",
                        eventId,
                        date
                );
            }
        }
    }


    @Override
    public void approveEvent(int eventId) {
        String sql = "UPDATE events SET status = 'approved' WHERE event_id = ?";
        jdbcTemplate.update(sql, eventId);
    }

    @Override
    public void uploadEventImage(int eventId, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Path.of("uploads"); // Adjust path as needed.
                Files.createDirectories(uploadPath);
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                String imageUrl = "/uploads/" + fileName;
                // Update the event record with the new image URL.
                String sql = "UPDATE events SET image_url = ? WHERE event_id = ?";
                jdbcTemplate.update(sql, imageUrl, eventId);
            } catch (Exception e) {
                throw new DaoException("Error storing image file", e);
            }
        }
    }

    @Override
    public void deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        jdbcTemplate.update(sql, eventId);
    }

    @Override
    public List<LocalDateTime> getEventDates(int eventId) {
        List<LocalDateTime> dates = new ArrayList<>();
        String sql = "SELECT event_date FROM event_dates WHERE event_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, eventId);
        while (rs.next()) {
            Timestamp ts = rs.getTimestamp("event_date");
            if (ts != null) {
                dates.add(ts.toLocalDateTime());
            }
        }
        return dates;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setTitle(rs.getString("title"));
        event.setDescription(rs.getString("description"));
        event.setCompanyName(rs.getString("company_name"));
        event.setVenueName(rs.getString("venue_name"));
        event.setVenueAddress(rs.getString("venue_address"));
        event.setTicketPrice(rs.getString("ticket_price"));
        event.setEventLink(rs.getString("event_link"));
        event.setEmailAddress(rs.getString("email_address"));
        event.setImageUrl(rs.getString("image_url"));
        event.setContentWarnings(rs.getString("content_warnings"));
        event.setStatus(rs.getString("status"));
        event.setMaskingPolicy(List.of(rs.getString("masking_policy").split(",")));
        event.setAccessibilityOptions(List.of(rs.getString("accessibility_options").split(",")));
        event.setOtherAccessibility(rs.getString("other_accessibility"));
        event.setCreatedBy(rs.getInt("created_by"));
        // Note: eventDates will be set after mapping by calling getEventDates().
        return event;
    }
}
