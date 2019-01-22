package com.peploleum.insight.graphy.repository;

import com.peploleum.insight.graphy.domain.DataJanus;
import com.peploleum.insight.graphy.dto.Criteria;

import java.util.List;

/**
 * Created by nicmir on 24/01/2019.
 */
public interface DataJanusRepositoryCustom {
    public List<DataJanus> findByCriteria(Criteria criteria);

    public DataJanus findOne(Long id);
}
