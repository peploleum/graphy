package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.service.ManagementServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ManagementResource {

    private final Logger log = LoggerFactory.getLogger(ManagementResource.class);

    private final ManagementServiceImpl managementService;

    public ManagementResource(ManagementServiceImpl managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/create-index")
    public ResponseEntity<List<Node>> createIndexes() {
        log.info("Creating indexes");
        return ResponseEntity.ok().build();
    }
}
