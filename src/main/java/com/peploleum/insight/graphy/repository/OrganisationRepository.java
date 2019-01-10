package com.peploleum.insight.graphy.repository;

import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import com.peploleum.insight.graphy.domain.Organisation;
import org.springframework.stereotype.Repository;

/**
 * Created by nicmir on 09/01/2019.
 */
@Repository
public interface OrganisationRepository extends GremlinRepository<Organisation, Long> {
}
