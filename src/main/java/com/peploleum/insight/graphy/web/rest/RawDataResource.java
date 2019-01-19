package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.RawData;
import com.peploleum.insight.graphy.dto.RawDataDTO;
import com.peploleum.insight.graphy.service.RawDataServiceImpl;
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

@RestController
@RequestMapping("/api")
public class RawDataResource {
    private final Logger log = LoggerFactory.getLogger(RawDataResource.class);

    private static final String ENTITY_NAME = "rawdata";

    private final RawDataServiceImpl rawDataService;

    public RawDataResource(RawDataServiceImpl rawDataService) {
        this.rawDataService = rawDataService;
    }

    @PostMapping("/raw-data")
    public ResponseEntity<Long> createRawData(@Valid @RequestBody RawDataDTO rawDataDTO) throws URISyntaxException {
        log.debug("REST request to save RawData : {}", rawDataDTO);

        Long result = rawDataService.save(rawDataDTO.getRawDataName(), rawDataDTO.getId(), rawDataDTO.getRawDataType());
        return ResponseEntity.created(new URI("/api/rawdata/" + result.toString()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString()))
                .body(result);
    }

    @GetMapping("/raw-data/{id}")
    public ResponseEntity<RawData> getRawData(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get RawData : {}", id);
        Optional<RawData> rawData = rawDataService.findOne(Long.valueOf(id));
        return ResponseUtil.wrapOrNotFound(rawData);
    }

    @DeleteMapping("/raw-data/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        log.debug("REST request to delete RawData : {}", id);
        rawDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
