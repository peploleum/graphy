package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.service.TraversalServiceImpl;
import org.apache.tinkerpop.gremlin.driver.Result;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class FeedTests extends BasicGraphyTests {

    private final Logger log = LoggerFactory.getLogger(FeedTests.class);

    @Autowired
    private TraversalServiceImpl traversalService;
    @Autowired
    private ApplicationContext context;

    @Autowired
    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    @Before
    public void setup() throws ClassNotFoundException {
        this.log.info("Setting up");
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
        this.template.getGremlinClient().submit("graph.tx().rollback()").all();
        this.template.getGremlinClient().submit("mgmt = graph.openManagement()").all();
        this.template.getGremlinClient().submit(" idInsight = mgmt.getPropertyKey('idInsight')").all();
        this.template.getGremlinClient().submit("mgmt.buildIndex('byNameComposite', Vertex.class).addKey(idInsight).buildCompositeIndex()").all();
        this.template.getGremlinClient().submit("mgmt.commit()").all();
    }

    @Test
    public void getNeighborsTest() throws Exception {
        Result one = this.template.getGremlinClient().submit("graph.openManagement().containsGraphIndex(\'byNameComposite\')").one();
        this.log.info(one.toString());
        String randomBiographicsId = null;
        for (int i = 0; i < 100; i++) {
            this.log.info("creating random biographics");
            randomBiographicsId = this.createRandomBiographics();
        }
        this.createSpecificBiographics();
        final Node byJanusId = this.traversalService.getByJanusId(randomBiographicsId);
        final Node properties = this.traversalService.getProperties(this.mongoId);
        Assert.assertNotNull(byJanusId);
        Assert.assertNotNull(properties);
    }

    @After
    public void remove() {

    }

}

