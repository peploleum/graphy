package com.peploleum.insight.graphy.service;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
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
}
