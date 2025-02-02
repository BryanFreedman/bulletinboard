package com.techelevator.dao;

import com.techelevator.model.Event;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface EventDao {
    // Submit an event without an image; image uploads are handled separately.
    void submitEvent(Event event);

    List<Event> getAllApprovedEvents(Integer userId);
    List<Event> getUserSubmissions(int userId);
    void approveEvent(int eventId);
    void deleteEvent(int eventId);
    boolean isEventFavorited(int userId, int eventId);

    // Retrieves the dates associated with a given event.
    List<LocalDateTime> getEventDates(int eventId);

    // Upload or update an event image.
    void uploadEventImage(int eventId, MultipartFile imageFile);
}
