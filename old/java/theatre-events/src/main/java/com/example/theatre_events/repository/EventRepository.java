package com.example.theatre_events.repository;


import com.example.theatre_events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.status = 'Approved'")
    List<Event> findApprovedEvents();
}
