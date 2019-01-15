package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.conversion.script.GremlinScriptLiteralVertex;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.microsoft.spring.data.gremlin.repository.support.GremlinEntityInformation;
import com.peploleum.insight.graphy.domain.Relation;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class GraphyApplicationTests extends BasicGraphyTests {


    @Autowired
    private ApplicationContext context;

    @Autowired
    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    private final Logger log = LoggerFactory.getLogger(GraphyApplicationTests.class);

    @Before
    public void setup() throws ClassNotFoundException {
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
    }

    @Test
    public void relationCreateTest() {
        createCustomGraph();
        this.template.findAll(new GremlinEntityInformation<>(Relation.class).createGremlinSource());
        Assert.assertEquals(Long.valueOf(20).longValue(), this.template.edgeCount());
        Assert.assertEquals(Long.valueOf(12).longValue(), this.template.vertexCount());
    }

    @Test
    public void emptyCountTest() {
        this.networkRepository.deleteAll();
        this.template.deleteAll();
        Assert.assertEquals(Long.valueOf(0).longValue(), this.template.edgeCount());
        Assert.assertEquals(Long.valueOf(0).longValue(), this.template.vertexCount());
    }

    @Test
    public void customQueryTest() {
        createCustomGraph();
        Assert.assertEquals(Long.valueOf(20).longValue(), this.template.edgeCount());
        final String pauls = GremlinScriptLiteralVertex.generateHas("biographicsName", "John");

        final ResultSet resultSet = this.template.getGremlinClient().submit("g.V()." + pauls);
        this.log.info("searhing by name: John");
        resultSet.stream().forEach(result -> {
            final LinkedHashMap resultObject = (LinkedHashMap) result.getObject();
            resultObject.keySet().stream().forEach((key -> {
                this.log.info(key + " - " + resultObject.get(key).toString());
            }));
        });
        this.log.info("searching by MongoId: " + mongoId);
        final String mongos = GremlinScriptLiteralVertex.generateHas("idInsight", mongoId);
        final ResultSet mongosResultSet = this.template.getGremlinClient().submit("g.V()." + mongos);
        mongosResultSet.stream().forEach(result -> {
            final LinkedHashMap resultObject = (LinkedHashMap) result.getObject();
            resultObject.keySet().stream().forEach((key -> {
                this.log.info(key + " - " + resultObject.get(key).toString());
            }));
        });
        final String neighborSuffix = ".outE().limit(50).inV().toList()";
        final String mongoIdQuery = GremlinScriptLiteralVertex.generateHas("idInsight", mongoId);
        final ResultSet neighborResultSet = this.template.getGremlinClient().submit("g.V()." + mongoIdQuery + neighborSuffix);
        this.log.info("Searching Relations");
        neighborResultSet.stream().forEach(result -> {
            this.log.info("------------");
            final LinkedHashMap resultObject = (LinkedHashMap) result.getObject();
            resultObject.keySet().stream().forEach((key -> {
                this.log.info(key + " - " + resultObject.get(key).toString());
            }));
        });
    }

    @After
    public void remove() {
        this.networkRepository.deleteAll();
        this.template.deleteAll();
    }

}

