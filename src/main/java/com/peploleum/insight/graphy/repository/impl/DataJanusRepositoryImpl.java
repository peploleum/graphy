package com.peploleum.insight.graphy.repository.impl;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.conversion.script.GremlinScriptLiteralVertex;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinOperations;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.domain.DataJanus;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.repository.DataJanusRepository;
import com.peploleum.insight.graphy.repository.DataJanusRepositoryCustom;
import com.peploleum.insight.graphy.web.rest.Type;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;

import java.security.Key;
import java.util.*;

/**
 * Created by nicmir on 24/01/2019.
 */
public class DataJanusRepositoryImpl implements DataJanusRepositoryCustom{

    private ApplicationContext context;

    @Autowired
    DataJanusRepository dataJanusRepository;

    private final Logger log = LoggerFactory.getLogger(DataJanusRepositoryImpl.class);

    GremlinOperations operations;

    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    public DataJanusRepositoryImpl(GremlinOperations operations, ApplicationContext context, GremlinFactory gremlinFactory) throws ClassNotFoundException
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
    public List<DataJanus> findByCriteria(Criteria criteria) {
        String name = "";
        if(!criteria.getValue().isEmpty()&&!criteria.getProperty().isEmpty())
            name = "."+GremlinScriptLiteralVertex.generateHas(criteria.getProperty(), criteria.getValue());
        String label = "";
        if(!criteria.getLabel().isEmpty())
            label = ".has(label,'"+criteria.getLabel()+"' )";
        this.log.info("Label : " + label);
        final ResultSet resultSet = this.template.getGremlinClient().submit("g.V()"+label+ name);
        this.log.info("searching by criteria:" + name);
        final List<DataJanus> dataJanusList = new ArrayList<>();
              resultSet.stream().forEach( key0 -> {
            this.log.info("key0 : " + key0);
                    dataJanusList.add(extract(key0));
                }
        );

        return dataJanusList;

    }

    @Override
    public DataJanus findOne(Long id) {
        return null;
    }

    @Override
    public List<DataJanus> findAllInOutVerticesByCriteria(Long id, Criteria criteria){
        String name = "";
        if(!criteria.getValue().isEmpty()&&!criteria.getProperty().isEmpty())
            name = "."+GremlinScriptLiteralVertex.generateHas(criteria.getProperty(), criteria.getValue());
        String label = "";
        if(!criteria.getLabel().isEmpty())
            label = ".has(label,'"+criteria.getLabel()+"' )";
        final ResultSet resultSet = this.template.getGremlinClient().submit("g.V("+id+").both()"+label+ name);
        this.log.info("searching by criteria:" + name);
        final List<DataJanus> dataJanusList = new ArrayList<>();

        resultSet.stream().forEach( key0 -> {
                    this.log.info("key0 : " + key0);
                    dataJanusList.add(extract(key0));
                }
        );
         return dataJanusList;
    }

    private DataJanus extract(Result key0){
        final DataJanus dataJanus = new DataJanus();
        final Map<String, String> emptyMap = new HashMap<String, String>();
        dataJanus.setProperties(emptyMap);
        LinkedHashMap resultObjTest = (LinkedHashMap) key0.getObject();
        resultObjTest.keySet().stream().forEach((key -> {
            this.log.info(key + " - " + resultObjTest.get(key).toString());
            if (key.toString().equals("id"))
                dataJanus.setId(Long.valueOf(resultObjTest.get(key).toString()));
            if (key.toString().equals("label"))
                dataJanus.setType(Type.valueOf(resultObjTest.get(key).toString()));
            if(resultObjTest.get(key) instanceof LinkedHashMap){
                final LinkedHashMap resultObject = (LinkedHashMap) resultObjTest.get(key);
                resultObject.keySet().stream().forEach((key1 -> {

                    ArrayList resultArray = (ArrayList) resultObject.get(key1);
                    LinkedHashMap linkedHashMap = (LinkedHashMap) resultArray.get(0);
                    this.log.info(key1 + " - " + linkedHashMap);
                    linkedHashMap.keySet().stream().forEach((key2 -> {
                        if(key2.toString().equals("value")){
                            if(key1.toString().equals("idInsight"))
                                dataJanus.setIdInsight(linkedHashMap.get(key2).toString());
                            dataJanus.putProperties(key1.toString(),linkedHashMap.get(key2).toString());
                        }
                        if(key2.toString().equals("value"))
                            this.log.info(key2 + " - " + linkedHashMap.get(key2).toString());
                    }));

                }));
            }
        }));
        return dataJanus;
    }
}
