package com.peploleum.insight.graphy.service;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

@Service
public class ManagementServiceImpl {

    private final Logger log = LoggerFactory.getLogger(TraversalServiceImpl.class);

    private ApplicationContext context;

    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    public ManagementServiceImpl(final ApplicationContext context, final GremlinFactory gremlinFactory) throws ClassNotFoundException {
        this.context = context;
        this.gremlinFactory = gremlinFactory;
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
    }

    /**
     * Creates a composite graph index for a given property name
     *
     * @param propertyKey the property name to index
     */
    public void buildIndex(final String propertyKey) {
        this.log.info("building index for property key: " + propertyKey);
        this.template.getGremlinClient().submit("graph.tx().rollback()").all();
        this.template.getGremlinClient().submit("mgmt = graph.openManagement()").all();
        this.template.getGremlinClient().submit("propertyKey = mgmt.getPropertyKey('" + propertyKey + "')").all();
        this.template.getGremlinClient().submit("mgmt.buildIndex('byNameComposite', Vertex.class).addKey(propertyKey).buildCompositeIndex()").all();
        final Result result = this.template.getGremlinClient().submit("graph.openManagement().containsGraphIndex(\'byNameComposite\')").one();
        this.log.info(result.toString());
        this.template.getGremlinClient().submit("mgmt.commit()").one();
    }
}
