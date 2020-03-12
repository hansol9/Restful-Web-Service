package com.hansol.restfulwebservice.event.repository;

import com.hansol.restfulwebservice.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
