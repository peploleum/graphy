package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.Organisation;
import com.peploleum.insight.graphy.dto.OrganisationDTO;
import com.peploleum.insight.graphy.service.OrganisationServiceImpl;
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
public class OrganisationResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationResource.class);

    private static final String ENTITY_NAME = "organisation";

    private final OrganisationServiceImpl organisationService;

    public OrganisationResource(OrganisationServiceImpl organisationService) {
        this.organisationService = organisationService;
    }

    @PostMapping("/organisation")
    public ResponseEntity<Long> createOrganisation(@Valid @RequestBody OrganisationDTO organisationDTO) throws URISyntaxException {
        log.debug("REST request to save Organisation : {}", organisationDTO);

        Long result = organisationService.save(organisationDTO.getOrganisationName(), organisationDTO.getId(), organisationDTO.getOrganisationDescrption());
        return ResponseEntity.created(new URI("/api/organisation/" + result.toString()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString()))
                .body(result);
    }

    @GetMapping("/organisation/{id}")
    public ResponseEntity<Organisation> getOrganisation(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Organisation : {}", id);
        Optional<Organisation> organisation = organisationService.findOne(Long.valueOf(id));
        return ResponseUtil.wrapOrNotFound(organisation);
    }

    @DeleteMapping("/organisation/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        log.debug("REST request to delete Organisation : {}", id);
        organisationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
