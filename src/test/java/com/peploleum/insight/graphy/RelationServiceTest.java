package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.conversion.script.GremlinScriptLiteralVertex;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.microsoft.spring.data.gremlin.repository.support.GremlinEntityInformation;
import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.service.EventServiceImpl;
import com.peploleum.insight.graphy.service.RelationServiceImpl;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Created by nicmir on 17/01/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class RelationServiceTest extends BasicGraphyTests{

    @Autowired
    private RelationServiceImpl relationService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    private final Logger log = LoggerFactory.getLogger(RelationServiceTest.class);

    @Before
    public void setup() throws ClassNotFoundException{
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
        this.template.getGremlinClient();
        createCustomGraph();
    }

    @Test
    public void relationFindOneTest() {
        final String mongoId = UUID.randomUUID().toString();
        final LinkedHashMap resultObject = this.relationService.findOne(getIdRelation());
        resultObject.keySet().stream().forEach((key -> {
            this.log.info(key + " - " + resultObject.get(key).toString());
        }));
        this.log.info("finished !!");
    }


    @Test
    public void relationDeleteTest() {
        createCustomGraph();
        final String mongoId = UUID.randomUUID().toString();
        this.relationService.delete(getIdRelation());
    }


    @After
    public void remove() {
        this.networkRepository.deleteAll();
        this.template.deleteAll();
    }

}
//g.V().has('propertykey','value1').outE('thatlabel').as('e').inV().has('propertykey','value2').select('e')