package com.peploleum.insight.graphy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peploleum.insight.graphy.dto.Criteria;
import com.peploleum.insight.graphy.dto.Node;
import com.peploleum.insight.graphy.web.rest.DataJanusResource;
import com.peploleum.insight.graphy.web.rest.TraversalResource;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by nicmir on 25/01/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphyApplication.class)
public class DataJanusResourceTests extends BasicGraphyTests{

    private final Logger log = LoggerFactory.getLogger(TraversalResourceTests.class);

    @Autowired
    private DataJanusResource dataJanusResource;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restTraversalMockMvc;

    @Before
    public void setup() {
        createCustomGraph();
    }

    @Test
    public void getAllInOutVerticesByCriteria() throws Exception {
        this.log.info("creating graph");
        createCustomGraph();
        this.log.info("calling TraversalResource");
        final Criteria criteria = new Criteria();
        criteria.setLabel("Biographics");
        criteria.setProperty("biographicsName");
        criteria.setValue("John");

        MockitoAnnotations.initMocks(this);
        this.restTraversalMockMvc = MockMvcBuilders.standaloneSetup(this.dataJanusResource)
                .setMessageConverters(jacksonMessageConverter).build();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        final byte[] bytes = mapper.writeValueAsBytes(criteria);
        this.log.info(mapper.writeValueAsString(criteria));
        ResultActions resultActions = this.restTraversalMockMvc.perform(post("/api/findByCriteria")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(bytes))
                .andExpect(status().isCreated());
        this.log.info("End " + resultActions.toString() );
    }

}
