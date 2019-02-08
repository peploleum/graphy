package com.peploleum.insight.graphy.repository;

import java.util.LinkedHashMap;

/**
 * Created by nicmir on 17/01/2019.
 */
public interface RelationRepositoryCustom {

    public void myDeleteById(String id);

    public LinkedHashMap findOne(String id);

    public void linkAll();

}
