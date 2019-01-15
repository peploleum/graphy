package com.peploleum.insight.graphy;

import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.service.TraversalServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class TraversalServiceTests extends BasicGraphyTests {

    private final Logger log = LoggerFactory.getLogger(TraversalServiceTests.class);
    //inner ring

    @Autowired
    private TraversalServiceImpl traversalService;

    @Before
    public void setup() {
        createCustomGraph();
    }

    @Test
    public void traversalTest() {
        final Node node = new Node();
        node.setType("Biographics");
        node.setId(this.mongoId);
        node.setLabel("Paul");
        final List<Node> neighbors = this.traversalService.getNeighbors(node);
        this.log.info(neighbors.size() + " neighbors");
        neighbors.forEach((neighbor -> {
            this.log.info(neighbor.toString());
        }));
    }

    @Test
    public void traversalMockTest() {
        final List<Node> neighbors = this.traversalService.getNeighborsMock(UUID.randomUUID().toString());
        this.log.info(neighbors.size() + " neighbors");
        neighbors.forEach((neighbor -> {
            this.log.info(neighbor.toString());
        }));
    }

    @Test
    public void generateNeighborsTest() {
        final List<Node> nodes = TraversalServiceImpl.generateNeighbors();
        this.log.info(nodes.size() + " neighbors");
        nodes.forEach((neighbor -> {
            this.log.info(neighbor.toString());
        }));
    }

    @After
    public void remove() {
        this.networkRepository.deleteAll();
    }
}

