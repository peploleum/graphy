package com.peploleum.insight.graphy.web.rest;

import com.peploleum.insight.graphy.domain.Equipment;
import com.peploleum.insight.graphy.dto.EquipmentDTO;
import com.peploleum.insight.graphy.service.EquipmentServiceImpl;
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
public class EquipmentResource {
    private final Logger log = LoggerFactory.getLogger(EquipmentResource.class);

    private static final String ENTITY_NAME = "equipment";

    private final EquipmentServiceImpl equipmentService;

    public EquipmentResource(EquipmentServiceImpl equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/equipment")
    public ResponseEntity<Long> createEquipment(@Valid @RequestBody EquipmentDTO equipmentDTO) throws URISyntaxException {
        log.debug("REST request to save Equipment : {}", equipmentDTO);
        Long result = equipmentService.save(equipmentDTO.getEquipmentName(), equipmentDTO.getId());
        return ResponseEntity.created(new URI("/api/equipment/" + result.toString()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.toString()))
                .body(result);
    }

    @GetMapping("/equipment/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get Equipment : {}", id);
        Optional<Equipment> equipment = equipmentService.findOne(Long.valueOf(id));
        return ResponseUtil.wrapOrNotFound(equipment);
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        log.debug("REST request to delete Equipment : {}", id);
        equipmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
