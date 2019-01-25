package com.peploleum.insight.graphy;

import com.peploleum.insight.graphy.domain.Biographics;
import com.peploleum.insight.graphy.domain.DataJanus;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import com.peploleum.insight.graphy.service.DataJanusServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by nicmir on 25/01/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class DataJanusServiceTests extends BasicGraphyTests{

    @Autowired
    private DataJanusServiceImpl dataJanusService;

    @Before
    public void setup()  {
        createCustomGraph();
    }


    @Test
    public void dataJanusFindByCriteria(){
        final List<DataJanus> dataJanusList = this.dataJanusService.findByCriteria(new Criteria("","equipmentName","RPG"));
        final List<DataJanus> dataJanusList1 = this.dataJanusService.findByCriteria(new Criteria("Biographics","",""));
    }

    @Test
    public void dataJanusFindAllInOutVerticesByCriteria(){
        final List<DataJanus> dataJanusList = this.dataJanusService.findAllInOutVerticesByCriteria(Long.valueOf("41112"), new Criteria("","biographicsName","John"));
    }
}
