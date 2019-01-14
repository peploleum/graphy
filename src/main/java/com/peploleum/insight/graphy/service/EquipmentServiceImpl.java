package com.peploleum.insight.graphy.service;

import com.peploleum.insight.graphy.domain.Equipment;
import com.peploleum.insight.graphy.repository.EquipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentServiceImpl {

    private final Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;

    }

    public Long save(String name, String idMongo) {
        log.debug("Request to save Equipment : {}");
        Equipment equipment = new Equipment();

        equipment.setEquipmentName(name);
        equipment.setIdInsight(idMongo);
        equipment.setEquipmentDescription("Sample description");

        equipment = equipmentRepository.save(equipment);
        Long result = equipment.getId();
        this.log.info("Vertex equipment saved: " + equipment.getId());

        return result;
    }

    public Optional<Equipment> findOne(Long id) {
        log.debug("Request to get equipment : {}", id);
        return equipmentRepository.findById(id);
    }

    /**
     * Delete the equipment by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete equipment : {}", id);
        equipmentRepository.deleteById(id);
    }

}
