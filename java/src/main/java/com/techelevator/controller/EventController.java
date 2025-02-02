package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.UserDao;  // Import the UserDao interface
import com.techelevator.model.Event;
import com.techelevator.model.User;
import com.techelevator.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/events")
public class EventController {

    private final EventDao eventDao;
    private final UserDao userDao;  // Declare the userDao dependency

    // Inject both dependencies via the constructor
    public EventController(EventDao eventDao, UserDao userDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    /**
     * Retrieve all approved events.
     */
    @GetMapping
    public ResponseEntity<List<Event>> getAllApprovedEvents() {
        // Use a default (null) if no user is logged in
        Integer userId = null;
        try {
            userId = SecurityUtils.getCurrentUserId();
        } catch (Exception e) {
            // No authenticated user; keep userId as null
        }
        return ResponseEntity.ok(eventDao.getAllApprovedEvents(userId));
    }

    /**
     * Submit a new event (JSON submission).
     */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitEvent(@RequestBody Event event) {
        // Get the numeric user ID from the security context.
        int userId = SecurityUtils.getCurrentUserId();

        // Instead of looking up by username, look up the user by ID.
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new IllegalStateException("User not found for user ID: " + userId);
        }
        event.setCreatedBy(user.getId());

        // Defensive check for event dates
        if (event.getEventDates() != null && !event.getEventDates().isEmpty()) {
            for (Object date : event.getEventDates()) {
                if (!(date instanceof LocalDateTime)) {
                    throw new RuntimeException("Invalid date type. Expected LocalDateTime but got: " + date.getClass().getName());
                }
            }
        }

        eventDao.submitEvent(event);
    }


    /**
     * Upload an event image (separate endpoint).
     */
    @PostMapping("/{eventId}/upload-image")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadEventImage(@PathVariable int eventId,
                                 @RequestParam("image") MultipartFile imageFile) {
        eventDao.uploadEventImage(eventId, imageFile);
    }

    /**
     * Retrieve events submitted by the logged-in user.
     */
    @GetMapping("/my-submissions")
    public ResponseEntity<List<Event>> getUserSubmissions() {
        int userId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(eventDao.getUserSubmissions(userId));
    }

    /**
     * Approve an event (Admin only).
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{eventId}/approve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approveEvent(@PathVariable int eventId) {
        eventDao.approveEvent(eventId);
    }

    /**
     * Delete an event (Admin only).
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable int eventId) {
        eventDao.deleteEvent(eventId);
    }
}
