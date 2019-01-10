package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.domain.Network;
import com.peploleum.insight.graphy.domain.Person;
import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.repository.BiographicsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nicmir on 09/01/2019.
 */
@Service
public class BiographicsServiceImpl /*implements BiographicsService*/ {

    private static final int AGE_THRESHOLD = 100;
    private final Logger log = LoggerFactory.getLogger(BiographicsServiceImpl.class);

    private final BiographicsRepository biographicsRepository;

    //private final BiographicsMapper biographicsMapper;

    //private final BiographicsSearchRepository biographicsSearchRepository;

    public BiographicsServiceImpl(BiographicsRepository biographicsRepository/*, BiographicsMapper biographicsMapper, BiographicsSearchRepository biographicsSearchRepository*/) {
        this.biographicsRepository = biographicsRepository;
        //this.biographicsMapper = biographicsMapper;
        //this.biographicsSearchRepository = biographicsSearchRepository;
    }

    /*@Override
    public BiographicsDTO save(BiographicsDTO biographicsDTO) {
        log.debug("Request to save Biographics : {}", biographicsDTO);

        Biographics biographics = biographicsMapper.toEntity(biographicsDTO);
        biographics = biographicsRepository.save(biographics);
        BiographicsDTO result = biographicsMapper.toDto(biographics);
        biographicsSearchRepository.save(biographics);
        return result;
    }*/

    public Long save(String name, String idMongo) {
        log.debug("Request to save Biographics : {}");
        Biographics biographics = new Biographics();

        biographics.setBiographicsName(name);
        final int randomThreshold = ThreadLocalRandom.current().nextInt(0, AGE_THRESHOLD);
        biographics.setBiographicsAge(randomThreshold);
        biographics.setIdInsight(idMongo);

        biographics = biographicsRepository.save(biographics);
        Long result = biographics.getId();
        this.log.info("Vertex Biographics saved: " + biographics.getId());
        //savedPersons.add(savedPerson);

        //this.network.getVertexes().addAll(savedPer sons);

        //final Network saved = this.networkRepo.save(this.network)
        return result;
    }

    /*@Override
    public Iterable<Biographics> findAll() {
        log.debug("Request to get all Biographics");
        return biographicsRepository.findAll().map(biographicsMapper::toDto);
    }*/


    /**
     * Get one biographics by id.
     *
     * @param id the id of the entity
     * @return the entity
     */

    public Optional<Biographics> findOne(Long id) {
        log.debug("Request to get Biographics : {}", id);
        return biographicsRepository.findById(id);
    }

    /**
     * Delete the biographics by id.
     *
     * @param id the id of the entity
     */

    public void delete(Long id) {
        log.debug("Request to delete Biographics : {}", id);
        biographicsRepository.deleteById(id);
    }

    /**
     * Search for the biographics corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    /*@Override
    public Page<BiographicsDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Biographics for query {}", query);
        return biographicsSearchRepository.search(queryStringQuery(query), pageable)
                .map(biographicsMapper::toDto);
    }*/
}
