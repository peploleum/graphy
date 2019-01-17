package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.service.TraversalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TraversalResource {

    private final Logger log = LoggerFactory.getLogger(TraversalResource.class);

    private final TraversalServiceImpl traversalService;

    public TraversalResource(TraversalServiceImpl traversalService) {
        this.traversalService = traversalService;
    }

    @PostMapping("/traversal")
    public ResponseEntity<List<Node>> traverse(@Valid @RequestBody Node sourceNode) throws URISyntaxException {
        log.debug("REST request to get neighbors for : {}", sourceNode);
        final List<Node> neighbors = this.traversalService.getNeighbors(sourceNode);
        return ResponseEntity.created(new URI("/api/traversal/"))
                .body(neighbors);
    }

    @GetMapping("/traversal/{id}")
    public ResponseEntity<List<Node>> getNeighbors(@PathVariable String id) throws URISyntaxException {
        final Node sourceNode = new Node();
        sourceNode.setId(id);
        sourceNode.setLabel("source");
        sourceNode.setType("source");
        log.debug("REST request to get neighbors for : {}", sourceNode);
        final List<Node> neighbors = this.traversalService.getNeighbors(sourceNode);
        return ResponseEntity.created(new URI("/api/traversal/"))
                .body(neighbors);
    }

    @GetMapping("/traversal/mock/{id}")
    public ResponseEntity<List<Node>> getNeighborsMock(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get neighbors for : {}", id);
        final List<Node> neighbors = this.traversalService.getNeighborsMock(id);

        return ResponseEntity.created(new URI("/api/traversal/"))
                .body(neighbors);
    }

    @GetMapping("/traversal/properties/{id}")
    public ResponseEntity<Node> getProperties(@PathVariable String id) throws URISyntaxException {
        log.info("REST request to get properties for : {}", id);
        final Node properties = this.traversalService.getProperties(id);
        return ResponseEntity.created(new URI("/api/traversal/properties"))
                .body(properties);
    }

    @GetMapping("/traversal/mock/properties/{id}")
    public ResponseEntity<Node> getPropertiesMock(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get properties for : {}", id);
        final Node properties = new Node();
        properties.setId(id);
        properties.setLabel("label");
        properties.setType("RawData");
        return ResponseEntity.created(new URI("/api/traversal/mock/properties"))
                .body(properties);
    }
}
