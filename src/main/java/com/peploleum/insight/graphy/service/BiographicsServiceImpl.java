package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.repository.BiographicsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BiographicsServiceImpl {

    private static final int AGE_THRESHOLD = 100;
    private final Logger log = LoggerFactory.getLogger(BiographicsServiceImpl.class);

    private final BiographicsRepository biographicsRepository;


    public BiographicsServiceImpl(BiographicsRepository biographicsRepository/*, BiographicsMapper biographicsMapper, BiographicsSearchRepository biographicsSearchRepository*/) {
        this.biographicsRepository = biographicsRepository;
    }

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
        return result;
    }

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

}
