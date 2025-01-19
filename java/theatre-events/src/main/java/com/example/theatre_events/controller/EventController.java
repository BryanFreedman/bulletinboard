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

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        // Save the event and associated showtimes
        event.getShowtimes().forEach(showtime -> showtime.setEvent(event));
        Event savedEvent = eventRepository.save(event);

        return ResponseEntity.status(201).body(savedEvent);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}

