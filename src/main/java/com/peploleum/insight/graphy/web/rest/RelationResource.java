package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.Type;
import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.dto.BiographicsDTO;
import com.peploleum.insight.graphy.dto.RelationDTO;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import com.peploleum.insight.graphy.service.RelationServiceImpl;
import com.peploleum.insight.graphy.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
    public class RelationResource {

    private final Logger log = LoggerFactory.getLogger(RelationResource.class);

    private static final String ENTITY_NAME = "relation";

    private final RelationServiceImpl relationService;

    public RelationResource(RelationServiceImpl relationService) {
        this.relationService = relationService;
    }


    @PostMapping("/relation")
    //@Timed(millis = 1000)
    public ResponseEntity<String> createRelation(@Valid @RequestBody RelationDTO relationDTO) throws URISyntaxException {
        log.debug("REST request to save Relation : {}", relationDTO);
        //if (biographics.getId() != null) {
        //throw new BadRequestAlertException("A new biographics cannot already have an ID", ENTITY_NAME, "idexists");
        //}
        log.info("relationDTO.getIdJanusCible" + relationDTO.getIdJanusCible());
        String result = relationService.save(Long.valueOf(relationDTO.getIdJanusSource()), Long.valueOf(relationDTO.getIdJanusCible()), relationDTO.getName(), Type.valueOf(relationDTO.getTypeSource()), Type.valueOf(relationDTO.getTypeCible()));
        return ResponseEntity.created(new URI("/api/relation/" + result))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result))
                .body(result);
    }

    /**
     * PUT  /biographics : Updates an existing biographics.
     *
     * @param biographicsDTO the biographicsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated biographicsDTO,
     * or with status 400 (Bad Request) if the biographicsDTO is not valid,
     * or with status 500 (Internal Server Error) if the biographicsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    /*@PutMapping("/biographics")
    @Timed
    public ResponseEntity<BiographicsDTO> updateBiographics(@Valid @RequestBody BiographicsDTO biographicsDTO) throws URISyntaxException {
        log.debug("REST request to update Biographics : {}", biographicsDTO);
        if (biographicsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BiographicsDTO result = biographicsService.save(biographicsDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, biographicsDTO.getId().toString()))
                .body(result);
    }*/

    /**
     * GET  /biographics : get all the biographics.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of biographics in body
     */
    /*@GetMapping("/biographics")
    @Timed
    public ResponseEntity<List<BiographicsDTO>> getAllBiographics(Pageable pageable) {
        log.debug("REST request to get a page of Biographics");
        Page<BiographicsDTO> page = biographicsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/biographics");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }*/

    /**
     * GET  /biographics/:id : get the "id" biographics.
     *
     * @param id the id of the biographicsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the biographicsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/biographics/{id}")
    //@Timed
    public ResponseEntity<Relation> getRelation(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Biographics : {}", id);
        Optional<Relation> relation = relationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relation);
    }

    /**
     * DELETE  /biographics/:id : delete the "id" biographics.
     *
     * @param id the id of the biographicsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/biographics/{id}")
    //@Timed
    public ResponseEntity<Void> deleteRelation(@PathVariable String id) {
        log.debug("REST request to delete Biographics : {}", id);
        relationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH  /_search/biographics?query=:query : search for the biographics corresponding
     * to the query.
     *
     * @param query the query of the biographics search
     * @param pageable the pagination information
     * @return the result of the search
     */
    /*@GetMapping("/_search/biographics")
    @Timed
    public ResponseEntity<List<Biographics>> searchBiographics(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Biographics for query {}", query);
        Page<BiographicsDTO> page = biographicsService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/biographics");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }*/

}
