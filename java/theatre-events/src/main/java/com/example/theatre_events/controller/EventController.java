package com.example.theatre_events.controller;

import com.example.theatre_events.model.Event;
import com.example.theatre_events.model.Showtime;
import com.example.theatre_events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Create a new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        // Link showtimes to the event before saving
        event.getShowtimes().forEach(showtime -> showtime.setEvent(event));
        Event savedEvent = eventRepository.save(event);
        return ResponseEntity.status(201).body(savedEvent);
    }

    // Get all events
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get only approved events
    @GetMapping("/approved")
    public List<Event> getApprovedEvents() {
        return eventRepository.findApprovedEvents();
    }
}
