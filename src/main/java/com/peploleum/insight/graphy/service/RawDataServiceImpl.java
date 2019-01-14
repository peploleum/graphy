package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.RawData;
import com.peploleum.insight.graphy.repository.RawDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RawDataServiceImpl {
    private final Logger log = LoggerFactory.getLogger(RawDataServiceImpl.class);

    private final RawDataRepository rawDataRepository;

    public RawDataServiceImpl(RawDataRepository rawDataRepository) {
        this.rawDataRepository = rawDataRepository;

    }

    /**
     * Saves a Raw Data Vertex
     *
     * @param name    name of the Raw Data element
     * @param idMongo unique Identifier in external database
     * @param type    Type of Raw Data
     * @return unique identifier in graph db
     */
    public Long save(String name, String idMongo, String type) {
        log.debug("Request to save rawData : {}");
        RawData rawData = new RawData();

        rawData.setRawDataName(name);
        rawData.setIdInsight(idMongo);
        rawData.setRawDataType(type);

        rawData = rawDataRepository.save(rawData);
        Long result = rawData.getId();
        this.log.info("Vertex rawData saved: " + rawData.getId());

        return result;
    }

    public Optional<RawData> findOne(Long id) {
        log.debug("Request to get rawData : {}", id);
        return rawDataRepository.findById(id);
    }

    /**
     * Delete the rawData by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete rawData : {}", id);
        rawDataRepository.deleteById(id);
    }
}
