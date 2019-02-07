package com.peploleum.insight.graphy.repository.impl;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinOperations;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.repository.RelationRepository;
import com.peploleum.insight.graphy.repository.RelationRepositoryCustom;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;

import java.util.LinkedHashMap;

/**
 * Created by nicmir on 17/01/2019.
 */
public class RelationRepositoryImpl implements RelationRepositoryCustom {

    private ApplicationContext context;

    @Autowired
    RelationRepository relationRepository;

    private final Logger log = LoggerFactory.getLogger(RelationRepositoryImpl.class);

    GremlinOperations operations;

    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    public RelationRepositoryImpl(GremlinOperations operations, ApplicationContext context, GremlinFactory gremlinFactory) throws ClassNotFoundException
    {
        this.context = context;
        this.gremlinFactory = gremlinFactory;
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
        this.template.getGremlinClient();
        this.operations = operations;
    }

    @Override
    public void myDeleteById(String id) {
        final ResultSet resultSet = this.template.getGremlinClient().submit("g.E('"+id+"').drop()");
    }
    @Override
    public LinkedHashMap findOne(String id) {
        final ResultSet resultSet = this.template.getGremlinClient().submit("g.E('"+id+"')");
        this.log.info("searching by id:" + id);
        LinkedHashMap resultObjTest = (LinkedHashMap) resultSet.one().getObject();
        return resultObjTest;
    }
}
