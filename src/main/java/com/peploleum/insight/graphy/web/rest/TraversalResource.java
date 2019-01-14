package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.service.TraversalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
}
