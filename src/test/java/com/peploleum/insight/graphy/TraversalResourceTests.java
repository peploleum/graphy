package com.peploleum.insight.graphy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.web.rest.TraversalResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class TraversalResourceTests extends BasicGraphyTests {

    private final Logger log = LoggerFactory.getLogger(TraversalResourceTests.class);

    @Autowired
    private TraversalResource traversalResource;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restTraversalMockMvc;

    @Before
    public void setup() {
        createCustomGraph();
    }

    @Test
    public void getNeighborsTest() throws Exception {
        this.log.info("creating graph");
        createCustomGraph();
        this.log.info("calling TraversalResource");
        final Node sourceNode = new Node();
        sourceNode.setLabel("John");
        sourceNode.setId(mongoId);
        sourceNode.setType("Biographics");

        MockitoAnnotations.initMocks(this);
        this.restTraversalMockMvc = MockMvcBuilders.standaloneSetup(this.traversalResource)
                .setMessageConverters(jacksonMessageConverter).build();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        final byte[] bytes = mapper.writeValueAsBytes(sourceNode);
        this.restTraversalMockMvc.perform(post("/api/traversal")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(bytes))
                .andExpect(status().isCreated());
    }

    @After
    public void remove() {
        this.networkRepository.deleteAll();
    }

}

