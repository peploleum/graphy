package com.peploleum.insight.graphy.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.peploleum.insight.graphy.domain.RelationBiographics;

/**
 * Created by nicmir on 10/01/2019.
 */
public interface RelationBiographicsRepository extends GremlinRepository<RelationBiographics, Long> {
}
