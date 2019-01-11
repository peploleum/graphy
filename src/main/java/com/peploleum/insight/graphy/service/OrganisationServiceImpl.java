package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Location;
import com.peploleum.insight.graphy.domain.Organisation;
import com.peploleum.insight.graphy.repository.LocationRepository;
import com.peploleum.insight.graphy.repository.OrganisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by nicmir on 11/01/2019.
 */
@Service
public class OrganisationServiceImpl {
    private final Logger log = LoggerFactory.getLogger(OrganisationServiceImpl.class);

    private final OrganisationRepository organisationRepository;

    public OrganisationServiceImpl(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;

    }

    public Long save(String name, String idMongo, String description) {
        log.debug("Request to save organisation : {}");
        Organisation organisation = new Organisation();

        organisation.setOrganisationName(name);
        organisation.setIdInsight(idMongo);
        organisation.setOrganisationDescrption(description);

        organisation = organisationRepository.save(organisation);
        Long result = organisation.getId();
        this.log.info("Vertex organisation saved: " + organisation.getId());

        return result;
    }

    public Optional<Organisation> findOne(Long id) {
        log.debug("Request to get organisation : {}", id);
        return organisationRepository.findById(id);
    }

    /**
     * Delete the organisation by id.
     *
     * @param id the id of the entity
     */

    public void delete(Long id) {
        log.debug("Request to delete organisation : {}", id);
        organisationRepository.deleteById(id);
    }

}
