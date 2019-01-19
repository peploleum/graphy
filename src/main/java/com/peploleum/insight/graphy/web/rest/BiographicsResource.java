package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.dto.BiographicsDTO;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import com.peploleum.insight.graphy.web.rest.util.HeaderUtil;
import com.peploleum.insight.graphy.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BiographicsResource {

    private final Logger log = LoggerFactory.getLogger(BiographicsResource.class);

    private static final String ENTITY_NAME = "biographics";

    private final BiographicsServiceImpl biographicsService;

    public BiographicsResource(BiographicsServiceImpl biographicsService) {
        this.biographicsService = biographicsService;
    }

    /**
     * POST  /biographics : Create a new biographics.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new biographicsDTO, or with status 400 (Bad Request) if the biographics has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/biographics")
    public ResponseEntity<Long> createBiographics(@Valid @RequestBody BiographicsDTO biographicsDTO) throws URISyntaxException {
        log.debug("REST request to save Biographics : {}", biographicsDTO);
        Long result = biographicsService.save(biographicsDTO.getBiographicsName(), biographicsDTO.getId());
        return ResponseEntity.created(new URI("/api/biographics/" + result.toString()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString()))
                .body(result);
    }


    /**
     * GET  /biographics/:id : get the "id" biographics.
     *
     * @param id the id of the biographicsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the biographicsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/biographics/{id}")
    public ResponseEntity<Biographics> getBiographics(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Biographics : {}", id);
        Optional<Biographics> biographics = biographicsService.findOne(Long.valueOf(id));
        return ResponseUtil.wrapOrNotFound(biographics);
    }

    @PostMapping("/biographics/findByCriteria")
    public ResponseEntity<Biographics> getBiographicsByCriteria(@Valid @RequestBody Criteria criteria) throws URISyntaxException {
        log.debug("REST request to get Biographics : {}", criteria.getProperty());
        Biographics biographics = biographicsService.findByCriteria(criteria);
        return new ResponseEntity<Biographics>(biographics, HttpStatus.OK);
    }

    /**
     * DELETE  /biographics/:id : delete the "id" biographics.
     *
     * @param id the id of the biographicsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/biographics/{id}")
    public ResponseEntity<Void> deleteBiographics(@PathVariable Long id) {
        log.debug("REST request to delete Biographics : {}", id);
        biographicsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}