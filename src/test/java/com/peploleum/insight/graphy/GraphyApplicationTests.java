package com.peploleum.insight.graphy;

import com.peploleum.insight.graphy.service.*;
import com.peploleum.insight.graphy.web.rest.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class GraphyApplicationTests {

    @Autowired
    private RelationServiceImpl relationService;

    @Autowired
    private BiographicsServiceImpl biographicsService;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private EquipmentServiceImpl equipmentService;

    @Autowired
    private OrganisationServiceImpl organisationService;

    @Autowired
    private RawDataServiceImpl rawDataService;

    @Autowired
    private LocationServiceImpl locationService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void relationCreateTest() {
        final String biographicsMongoId = UUID.randomUUID().toString();
        final Long biographicsId = this.biographicsService.save("Paul", biographicsMongoId);
        final String rawDataMongoId = UUID.randomUUID().toString();
        final Long rawDataId = this.rawDataService.save("Tweet", rawDataMongoId, "TWITTER");
        final String organisationMongoId = UUID.randomUUID().toString();
        final Long organisationId = this.organisationService.save("UN", organisationMongoId, "United Nations");
        final String equipementMongoId = UUID.randomUUID().toString();
        final Long equipmentId = this.equipmentService.save("Gun", equipementMongoId);
        final String eventMongoId = UUID.randomUUID().toString();
        final Long eventId = this.eventService.save("Meeting", eventMongoId);
        final String locationMongoId = UUID.randomUUID().toString();
        final Long locationId = this.locationService.save("Paris", locationMongoId);

        this.relationService.save(rawDataId, biographicsId, "linked to", Type.RawData, Type.Biographics);
        this.relationService.save(rawDataId, eventId, "linked to", Type.RawData, Type.Event);
        this.relationService.save(rawDataId, organisationId, "linked to", Type.RawData, Type.Organisation);
        this.relationService.save(rawDataId, equipmentId, "linked to", Type.RawData, Type.Equipment);
        this.relationService.save(rawDataId, locationId, "linked to", Type.RawData, Type.Location);

    }

}

