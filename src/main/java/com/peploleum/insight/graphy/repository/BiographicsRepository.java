package com.peploleum.insight.graphy.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.peploleum.insight.graphy.domain.Biographics;
import org.springframework.stereotype.Repository;

/**
 * Created by nicmir on 09/01/2019.
 */
@Repository
public interface BiographicsRepository  extends GremlinRepository<Biographics, Long> {
}
