package com.peploleum.insight.graphy.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.peploleum.insight.graphy.domain.DataJanus;

/**
 * Created by nicmir on 24/01/2019.
 */
public interface DataJanusRepository extends GremlinRepository<DataJanus, Long>, DataJanusRepositoryCustom{
}
