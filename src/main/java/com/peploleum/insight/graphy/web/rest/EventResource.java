package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.domain.Event;
import com.peploleum.insight.graphy.dto.BiographicsDTO;
import com.peploleum.insight.graphy.dto.EventDTO;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import com.peploleum.insight.graphy.service.EventServiceImpl;
import com.peploleum.insight.graphy.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Created by nicmir on 11/01/2019.
 */
@RestController
@RequestMapping("/api")
public class EventResource {
    private final Logger log = LoggerFactory.getLogger(EventResource.class);

    private static final String ENTITY_NAME = "event";

    private final EventServiceImpl eventService;

    public EventResource(EventServiceImpl eventService) {
        this.eventService = eventService;
    }
    @PostMapping("/event")
    public ResponseEntity<Long> createEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to save Event : {}", eventDTO);
        Long result = eventService.save(eventDTO.getName(), eventDTO.getIdMongo());
        return ResponseEntity.created(new URI("/api/event/" + result.toString()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString()))
                .body(result);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Event : {}", id);
        Optional<Event> event = eventService.findOne(Long.valueOf(id));
        return ResponseUtil.wrapOrNotFound(event);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.debug("REST request to delete Event : {}", id);
        eventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
