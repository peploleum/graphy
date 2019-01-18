package com.peploleum.insight.graphy;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class BiographicsServiceTest extends BasicGraphyTests {

    @Autowired
    private BiographicsServiceImpl biographicsService;

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
}

