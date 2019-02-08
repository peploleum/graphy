package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.repository.NetworkRepository;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import com.peploleum.insight.graphy.service.TraversalServiceImpl;
import com.peploleum.insight.graphy.web.rest.Type;
import org.apache.tinkerpop.gremlin.driver.Result;
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

import java.util.UUID;

/**
 * Created by nicmir on 01/02/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class LimitTest {

    private final Logger log = LoggerFactory.getLogger(BasicGraphyTests.class);

    @Autowired
    private BiographicsServiceImpl biographicsService;

    @Autowired
    protected NetworkRepository networkRepository;

    @Autowired
    private TraversalServiceImpl traversalService;
    @Autowired
    private ApplicationContext context;

    @Autowired
    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    protected String mongoId = UUID.randomUUID().toString();

    @Before
    public void setup() throws ClassNotFoundException {
        this.log.info("setting up");
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
    }

    public void limitTest() {
        Result one = this.template.getGremlinClient().submit("graph.openManagement().containsGraphIndex(\'insightIdNameComposite\')").one();
        this.log.info(one.toString());
        String randomBiographicsId = null;
        for (int i = 0; i < 100000; i++) {
            this.log.info("creating random biographics jabusId");
            randomBiographicsId = this.createRandomBiographics();
        }
        this.createSpecificBiographics();
    }

    public String createRandomBiographics() {
        final String outerBiographicsMongoId = UUID.randomUUID().toString();
        final Long outerBiographicsId = this.biographicsService.save(UUID.randomUUID().toString(), outerBiographicsMongoId);
        return String.valueOf(outerBiographicsId);
    }

    public String createSpecificBiographics() {
        final Long outerBiographicsId = this.biographicsService.save(UUID.randomUUID().toString(), this.mongoId);
        return String.valueOf(outerBiographicsId);
    }

    @Test
    public void injectionTest() {
        Assert.assertNotNull(this.networkRepository);
        Assert.assertNotNull(this.biographicsService);
    }

}
