package com.peploleum.insight.graphy.repository.impl;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.conversion.script.GremlinScriptLiteralVertex;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinOperations;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.repository.BiographicsRepositoryCustom;
import com.peploleum.insight.graphy.repository.RelationRepository;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nicmir on 18/01/2019.
 */
public class BiographicsRepositoryImpl implements BiographicsRepositoryCustom{
    private ApplicationContext context;

    @Autowired
    RelationRepository relationRepository;

    private final Logger log = LoggerFactory.getLogger(BiographicsRepositoryImpl.class);

    GremlinOperations operations;

    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    protected String mongoId = UUID.randomUUID().toString();

    public BiographicsRepositoryImpl(GremlinOperations operations, ApplicationContext context, GremlinFactory gremlinFactory) throws ClassNotFoundException
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
    public Biographics findByCriteria(Criteria criteria) {
        final String name = GremlinScriptLiteralVertex.generateHas(criteria.getProperty(), criteria.getValue());
        final String label = GremlinScriptLiteralVertex.generateHas("Label", "Biographics");
        final ResultSet resultSet = this.template.getGremlinClient().submit("g.V()." + label + "."+ name);
        this.log.info("searching by criteria:" + name);
        final Biographics biographics = new Biographics();
        LinkedHashMap resultObjTest = (LinkedHashMap) resultSet.one().getObject();

            resultObjTest.keySet().stream().forEach((key -> {
                this.log.info(key + " - " + resultObjTest.get(key).toString());
                if (key.toString().equals("id"))
                    biographics.setId(Long.valueOf(resultObjTest.get(key).toString()));
                if(resultObjTest.get(key) instanceof LinkedHashMap){
                    final LinkedHashMap resultObject = (LinkedHashMap) resultObjTest.get(key);
                     resultObject.keySet().stream().forEach((key1 -> {

                        ArrayList resultArray = (ArrayList) resultObject.get(key1);
                        LinkedHashMap linkedHashMap = (LinkedHashMap) resultArray.get(0);
                        this.log.info(key1 + " - " + linkedHashMap);
                        linkedHashMap.keySet().stream().forEach((key2 -> {
                            if(key2.toString().equals("value")){
                                switch (key1.toString()){
                                    case "biographicsName":
                                        biographics.setBiographicsName(linkedHashMap.get(key2).toString());
                                        break;
                                    case "idInsight":
                                        biographics.setIdInsight(linkedHashMap.get(key2).toString());
                                        break;
                                    case "biographicsAge":
                                        biographics.setBiographicsAge(Integer.valueOf(linkedHashMap.get(key2).toString()));
                                        break;
                                }
                            }
                            if(key2.toString().equals("value"))
                            this.log.info(key2 + " - " + linkedHashMap.get(key2).toString());
                        }));

                    }));
                }
            }));

        return biographics;

    }

    @Override
    public void createMassBiographics(String num) {

    }



}
