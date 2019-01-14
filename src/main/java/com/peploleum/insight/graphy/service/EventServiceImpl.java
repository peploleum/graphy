package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Event;
import com.peploleum.insight.graphy.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by nicmir on 11/01/2019.
 */
@Service
public class EventServiceImpl {
    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

    }

    public Long save(String name, String idMongo) {
        log.debug("Request to save event : {}");
        Event event = new Event();

        event.setEventName(name);
        event.setIdInsight(idMongo);
        event.setEventDescription("Sample description");

        event = eventRepository.save(event);
        Long result = event.getId();
        this.log.info("Vertex equipment saved: " + event.getId());

        return result;
    }

    public Optional<Event> findOne(Long id) {
        log.debug("Request to get event : {}", id);
        return eventRepository.findById(id);
    }

    /**
     * Delete the event by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete event : {}", id);
        eventRepository.deleteById(id);
    }
}
