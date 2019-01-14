package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.web.rest.Type;
import com.peploleum.insight.graphy.domain.*;
import com.peploleum.insight.graphy.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationServiceImpl {

    private final Logger log = LoggerFactory.getLogger(RelationServiceImpl.class);

    private final RelationRepository relationRepository;

    @Autowired
    private final BiographicsRepository biographicsRepository;
    private final EquipmentRepository equipmentRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final OrganisationRepository organisationRepository;
    private final RawDataRepository rawDataRepository;

    public RelationServiceImpl(RelationRepository relationRepository, BiographicsRepository biographicsRepository, EquipmentRepository equipmentRepository, EventRepository eventRepository, LocationRepository locationRepository, OrganisationRepository organisationRepository, RawDataRepository rawDataRepository) {
        this.relationRepository = relationRepository;
        this.biographicsRepository = biographicsRepository;
        this.equipmentRepository = equipmentRepository;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.organisationRepository = organisationRepository;
        this.rawDataRepository = rawDataRepository;
    }

    public String save(Long idSource, Long idCible, String name, Type typeSource, Type typeCible) {

        log.debug("Request to save Relation : {}");
        Relation relation = new Relation();

        switch (typeSource) {
            case Event:
                Event eventSource = eventRepository.findById(idSource).get();
                relation.setObjectFrom(eventSource);
                break;
            case RawData:
                RawData rawDataSource = rawDataRepository.findById(idSource).get();
                relation.setObjectFrom(rawDataSource);
                break;
            case Location:
                Location locationSource = locationRepository.findById(idSource).get();
                relation.setObjectFrom(locationSource);
                break;
            case Equipment:
                Equipment equipmentSource = equipmentRepository.findById(idSource).get();
                relation.setObjectFrom(equipmentSource);
                break;
            case Biographics:
                Biographics biographicsTypeSource = biographicsRepository.findById(idSource).get();
                relation.setObjectFrom(biographicsTypeSource);
                break;
            case Organisation:
                Organisation organisationSource = organisationRepository.findById(idSource).get();
                relation.setObjectFrom(organisationSource);
                break;
        }
        switch (typeCible) {
            case Event:
                Event eventCible = eventRepository.findById(idCible).get();
                relation.setObjectTo(eventCible);
                break;
            case RawData:
                RawData rawDataCible = rawDataRepository.findById(idCible).get();
                relation.setObjectTo(rawDataCible);
                break;
            case Location:
                Location locationCible = locationRepository.findById(idCible).get();
                relation.setObjectTo(locationCible);
                break;
            case Equipment:
                Equipment equipmentCible = equipmentRepository.findById(idCible).get();
                relation.setObjectTo(equipmentCible);
                break;
            case Biographics:
                Biographics biographicTypeCible = biographicsRepository.findById(idCible).get();
                relation.setObjectTo(biographicTypeCible);
                break;
            case Organisation:
                Organisation organisationCible = organisationRepository.findById(idCible).get();
                relation.setObjectTo(organisationCible);
                break;
        }


        relation.setName(name);

        relation = relationRepository.save(relation);
        String result = relation.getId();
        this.log.info("Relation between : " + relation.getObjectFrom().toString() + " and " + relation.getObjectTo().toString() + " saved: " + relation.getId());
        return result;
    }


    /**
     * Get one biographics by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<Relation> findOne(String id) {
        log.debug("Request to get Biographics : {}", id);
        return relationRepository.findById(id);
    }

    /**
     * Delete the biographics by id.
     *
     * @param id the id of the entity
     */

    public void delete(String id) {
        log.debug("Request to delete Biographics : {}", id);
        relationRepository.deleteById(id);
    }
}
