package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.dto.RelationDTO;
import com.peploleum.insight.graphy.service.RelationServiceImpl;
import com.peploleum.insight.graphy.web.rest.util.HeaderUtil;
import com.peploleum.insight.graphy.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Optional;

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
    public ResponseEntity<String> createRelation(@Valid @RequestBody RelationDTO relationDTO) throws URISyntaxException {
        log.debug("REST request to save Relation : {}", relationDTO);
        log.info("relationDTO.getIdJanusCible" + relationDTO.getIdJanusCible());
        String result = relationService.save(Long.valueOf(relationDTO.getIdJanusSource()), Long.valueOf(relationDTO.getIdJanusCible()), relationDTO.getName(), Type.valueOf(relationDTO.getTypeSource()), Type.valueOf(relationDTO.getTypeCible()));
        return ResponseEntity.created(new URI("/api/relation/" + result))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result))
                .body(result);
    }

    /**
     * GET  /biographics/:id : get the "id" biographics.
     *
     * @param id the id of the biographicsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the biographicsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/relation/{id}")
    public @ResponseBody String getRelation(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Biographics : {}", id);
        LinkedHashMap relation = relationService.findOne(id);
        return relation.get(1).toString();
    }

    /**
     * DELETE  /biographics/:id : delete the "id" biographics.
     *
     * @param id the id of the biographicsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/relation/{id}")
    public ResponseEntity<Void> deleteRelation(@PathVariable String id) {
        log.debug("REST request to delete Biographics : {}", id);
        relationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
