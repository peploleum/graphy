package com.peploleum.insight.graphy.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.peploleum.insight.graphy.domain.Equipment;
import org.springframework.stereotype.Repository;

/**
 * Created by nicmir on 09/01/2019.
 */
@Repository
public interface EquipmentRepository extends GremlinRepository<Equipment, Long> {
}
