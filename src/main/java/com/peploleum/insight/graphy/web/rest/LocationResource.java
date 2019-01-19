package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.Location;
import com.peploleum.insight.graphy.dto.LocationDTO;
import com.peploleum.insight.graphy.service.LocationServiceImpl;
import com.peploleum.insight.graphy.web.rest.util.HeaderUtil;
import com.peploleum.insight.graphy.web.rest.util.ResponseUtil;
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
public class LocationResource {
    private final Logger log = LoggerFactory.getLogger(LocationResource.class);

    private static final String ENTITY_NAME = "location";

    private final LocationServiceImpl locationService;

    public LocationResource(LocationServiceImpl locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<Long> createLocation(@Valid @RequestBody LocationDTO locationDTO) throws URISyntaxException {
        log.debug("REST request to save Biographics : {}", locationDTO);
        Long result = locationService.save(locationDTO.getLocationName(), locationDTO.getId());
        return ResponseEntity.created(new URI("/api/location/" + result.toString()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString()))
                .body(result);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Location : {}", id);
        Optional<Location> location = locationService.findOne(Long.valueOf(id));
        return ResponseUtil.wrapOrNotFound(location);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        log.debug("REST request to delete Location : {}", id);
        locationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
