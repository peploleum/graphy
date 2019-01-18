package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Persistent;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class BiographicsServiceTest extends BasicGraphyTests {

    @Autowired
    private BiographicsServiceImpl biographicsService;

    @Before
    public void setup()  {
        createCustomGraph();
    }

    @Test
    public void biographicsCreateTest() {
        final String mongoId = UUID.randomUUID().toString();
        final Long biographicsId = this.biographicsService.save("Paul", mongoId);
    }

    @Test
    public void biographicsFindByIdTest(){
        final String mongoId = UUID.randomUUID().toString();
        final Optional<Biographics> biographicsId = this.biographicsService.findOne(Long.valueOf(getIdRelation()));
    }

    @Test
    public void biographicsFindByCriteria(){
        final Biographics biographicsId = this.biographicsService.findByCriteria(new Criteria("idInsight","f44ae798-c648-4f03-b9fd-dc67324212f0"));
    }

}

