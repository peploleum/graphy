package com.peploleum.insight.graphy;

import com.peploleum.insight.graphy.service.RawDataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class RawDataServiceTest {

    @Autowired
    private RawDataServiceImpl rawDataService;

    @Test
    public void rawDataCreateTest() {
        final String mongoId = UUID.randomUUID().toString();
        final Long rawDataId = this.rawDataService.save("Tweet", mongoId, "TWITTER");
    }
}

