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
    }

    @Test
    public void feedTest() throws Exception {
        Result one = this.template.getGremlinClient().submit("graph.openManagement().containsGraphIndex(\'insightIdNameComposite\')").one();
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

    @Test
    public void singleFeedTest() {
        Result one = this.template.getGremlinClient().submit("graph.openManagement().containsGraphIndex(\'insightIdNameComposite\')").one();
        this.log.info(one.toString());
        final String specificBiographics = this.createSpecificBiographics();
        final Node byJanusId = this.traversalService.getByJanusId(specificBiographics);
        final Node properties = this.traversalService.getProperties(this.mongoId);
        Assert.assertNotNull(byJanusId);
        Assert.assertNotNull(properties);
    }

    @Test
    public void checkIndex() {
        Result one = this.template.getGremlinClient().submit("graph.openManagement().containsGraphIndex(\'byNameComposite\')").one();
        this.log.info(one.toString());
        final String specificBiographics = this.createSpecificBiographics();
        final Node byJanusId = this.traversalService.getByJanusId(specificBiographics);
        final Node properties = this.traversalService.getProperties(this.mongoId);
        Assert.assertNotNull(byJanusId);
        Assert.assertNotNull(properties);
    }
}

