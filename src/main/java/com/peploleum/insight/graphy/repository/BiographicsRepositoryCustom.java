package com.peploleum.insight.graphy.repository;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.dto.Criteria;

/**
 * Created by nicmir on 18/01/2019.
 */
public interface BiographicsRepositoryCustom {

    public Biographics findByCriteria(Criteria criteria);
}
