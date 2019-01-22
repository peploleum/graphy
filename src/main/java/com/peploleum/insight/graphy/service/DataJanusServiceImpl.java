package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.domain.DataJanus;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.repository.BiographicsRepository;
import com.peploleum.insight.graphy.repository.DataJanusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nicmir on 24/01/2019.
 */
@Service
public class DataJanusServiceImpl {

    private static final int AGE_THRESHOLD = 100;
    private final Logger log = LoggerFactory.getLogger(BiographicsServiceImpl.class);

    private final DataJanusRepository dataJanusRepository;


    public DataJanusServiceImpl(DataJanusRepository dataJanusRepository/*, BiographicsMapper biographicsMapper, BiographicsSearchRepository biographicsSearchRepository*/) {
        this.dataJanusRepository = dataJanusRepository;
    }

    /*public Long save(String name, String idMongo) {
        log.debug("Request to save Biographics : {}");
        DataJanus dataJanus = new DataJanus();

        dataJanus.setBiographicsName(name);
        final int randomThreshold = ThreadLocalRandom.current().nextInt(0, AGE_THRESHOLD);
        biographics.setBiographicsAge(randomThreshold);
        biographics.setIdInsight(idMongo);

        biographics = biographicsRepository.save(biographics);
        Long result = biographics.getId();
        this.log.info("Vertex Biographics saved: " + biographics.getId());
        return result;
    }*/

    /**
     * Get one biographics by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<DataJanus> findOne(Long id) {
        log.debug("Request to get Biographics : {}", id);
        return dataJanusRepository.findById(id);
    }

    public List<DataJanus> findByCriteria(Criteria criteria){
        log.debug("Request to get Biographics by Criteria", criteria.getProperty());
        List<DataJanus> dataJanusList = dataJanusRepository.findByCriteria(criteria);
        log.info(dataJanusList.toString());
        return dataJanusList;
    }

    /**
     * Delete the biographics by id.
     *
     * @param id the id of the entity
     */

    public void delete(Long id) {
        log.debug("Request to delete Biographics : {}", id);
        dataJanusRepository.deleteById(id);
    }


}
