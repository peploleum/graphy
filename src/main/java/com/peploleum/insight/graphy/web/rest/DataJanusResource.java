package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.DataJanus;
import com.peploleum.insight.graphy.domain.RawData;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.dto.RawDataDTO;
import com.peploleum.insight.graphy.service.DataJanusServiceImpl;
import com.peploleum.insight.graphy.service.RawDataServiceImpl;
import com.peploleum.insight.graphy.web.rest.util.HeaderUtil;
import com.peploleum.insight.graphy.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by nicmir on 25/01/2019.
 */
@RestController
@RequestMapping("/api")
public class DataJanusResource {
    private final Logger log = LoggerFactory.getLogger(DataJanusResource.class);

    private static final String ENTITY_NAME = "dataJanus";

    private final DataJanusServiceImpl dataJanusService;

    public DataJanusResource(DataJanusServiceImpl dataJanusService) {
        this.dataJanusService = dataJanusService;
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<DataJanus>> getDataJanusListByCriteria(@Valid @RequestBody Criteria criteria) throws URISyntaxException {
        log.debug("REST request to get RawData : {}", criteria.getProperty());
        List<DataJanus> dataJanusList = dataJanusService.findByCriteria(criteria);
        return new ResponseEntity<List<DataJanus>>(dataJanusList, HttpStatus.OK);
    }

    @PostMapping("/findAllInOutVerticesByCriteria")
    public ResponseEntity<List<DataJanus>> getAllInOutVerticesByCriteria(@Valid @RequestBody Criteria criteria) throws URISyntaxException {
        log.debug("REST request to get RawData : {}", criteria.getProperty());
        List<DataJanus> dataJanusList = dataJanusService.findByCriteria(criteria);
        return new ResponseEntity<List<DataJanus>>(dataJanusList, HttpStatus.OK);
    }

}
