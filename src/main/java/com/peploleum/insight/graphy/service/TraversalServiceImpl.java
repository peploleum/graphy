package com.peploleum.insight.graphy.service;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.microsoft.spring.data.gremlin.conversion.MappingGremlinConverter;
import com.microsoft.spring.data.gremlin.conversion.script.GremlinScriptLiteralVertex;
import com.microsoft.spring.data.gremlin.mapping.GremlinMappingContext;
import com.microsoft.spring.data.gremlin.query.GremlinTemplate;
import com.peploleum.insight.graphy.dto.Node;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Service
public class TraversalServiceImpl {

    private final Logger log = LoggerFactory.getLogger(TraversalServiceImpl.class);

    private ApplicationContext context;

    private GremlinFactory gremlinFactory;

    private GremlinTemplate template;

    public TraversalServiceImpl(final ApplicationContext context, final GremlinFactory gremlinFactory) throws ClassNotFoundException {
        this.context = context;
        this.gremlinFactory = gremlinFactory;
        final GremlinMappingContext mappingContext = new GremlinMappingContext();
        mappingContext.setInitialEntitySet(new EntityScanner(this.context).scan(Persistent.class));
        final MappingGremlinConverter converter = new MappingGremlinConverter(mappingContext);
        this.template = new GremlinTemplate(this.gremlinFactory, converter);
    }

    /**
     * Fetches the neighbors for a given node. Source vertex is chosen based on 'id' property and all outgoing edges are walked (limit 50)
     *
     * @param node
     * @return the neighbors
     */
    public List<Node> getNeighbors(final Node node) {
        final List<Node> nodeList = new ArrayList<>();
        final String id = node.getId();
        this.log.info("searching by external Id: " + id + " and traversing outgoing edges to get neighbors");
        final String neighborSuffix = ".outE().limit(50).inV().toList()";
        final String mongoIdQuery = GremlinScriptLiteralVertex.generateHas("idInsight", id);
        final ResultSet neighborResultSet = this.template.getGremlinClient().submit("g.V()." + mongoIdQuery + neighborSuffix);
        this.log.info("Parsing neighbors");
        neighborResultSet.stream().forEach(result -> {
            this.log.info("------------");
            final LinkedHashMap resultObject = (LinkedHashMap) result.getObject();
            resultObject.keySet().stream().forEach((key -> {
                this.log.info(key + " - " + resultObject.get(key).toString());
            }));
            final Node neighbor = new Node();
            final String type = resultObject.get("label").toString();
            neighbor.setType(type);
            final String idInsight = smartOpenProperties(resultObject, "idInsight");
            String searchKey = findSearchKey(type);
            if (searchKey != null) {
                neighbor.setId(idInsight);
                final String label = smartOpenProperties(resultObject, searchKey);
                if (label != null) {
                    neighbor.setLabel(label);
                    nodeList.add(neighbor);
                    this.log.info("adding node: " + neighbor.toString());
                }
            }
        });
        return nodeList;
    }

    public Node getProperties(final String id) {
        this.log.info("Searching Node with idInsight property: " + id);
        final String mongoIdQuery = GremlinScriptLiteralVertex.generateHas("idInsight", id);
        final long count = this.template.vertexCount();
        this.log.info("COUNT: " + count);
        final String gremlinQuery = "g.V()." + mongoIdQuery + ".next()";
        this.log.info("GremlinQuery: " + gremlinQuery);
        final ResultSet resultSet = this.template.getGremlinClient().submit(gremlinQuery);
        this.log.info("Parsing first result");
        final Node foundNode = new Node();
        final Result result = resultSet.one();
//        final Result result = resultSet.stream().findFirst().get();
        if (result == null) {
            this.log.info("No result");
            return null;
        }
        this.log.info("Found result");
        final LinkedHashMap resultObject = (LinkedHashMap) result.getObject();
        final String type = resultObject.get("label").toString();
        foundNode.setType(type);
        foundNode.setId(id);
        final String searchKey = findSearchKey(type);
        this.log.info("Searching key: " + searchKey);
        if (searchKey != null) {
            final String label = smartOpenProperties(resultObject, searchKey);
            if (label != null) {
                foundNode.setLabel(label);
                this.log.info("found node: " + foundNode.toString());
            }
        }
        return foundNode;
    }

    public Node getByJanusId(final String id) {
        this.log.info("Searching Node with janusId property: " + id);
        final String gremlinQuery = "g.V(" + id + ")";
        this.log.info("GremlinQuery: " + gremlinQuery);
        final ResultSet resultSet = this.template.getGremlinClient().submit(gremlinQuery);
        this.log.info("Parsing first result");
        final Node foundNode = new Node();
        final Result result = resultSet.one();
//        final Result result = resultSet.stream().findFirst().get();
        if (result == null) {
            this.log.info("No result");
            return null;
        }
        this.log.info("Found result");
        final LinkedHashMap resultObject = (LinkedHashMap) result.getObject();
        final String type = resultObject.get("label").toString();
        foundNode.setType(type);
        foundNode.setId(id);
        final String searchKey = findSearchKey(type);
        this.log.info("Searching key: " + searchKey);
        if (searchKey != null) {
            final String label = smartOpenProperties(resultObject, searchKey);
            if (label != null) {
                foundNode.setLabel(label);
                this.log.info("found node: " + foundNode.toString());
            }
        }
        return foundNode;
    }

    private String findSearchKey(String type) {
        String searchKey = null;
        switch (type) {
            case "Biographics":
                searchKey = "biographicsName";
                break;
            case "Event":
                searchKey = "eventName";
                break;
            case "Equipment":
                searchKey = "equipmentName";
                break;
            case "Location":
                searchKey = "locationName";
                break;
            case "RawData":
                searchKey = "rawDataName";
                break;
            case "Organisation":
                searchKey = "organisationName";
                break;
            default:
                break;
        }
        return searchKey;
    }

    private String smartOpenProperties(LinkedHashMap object, String key) {
        final LinkedHashMap properties = (LinkedHashMap) object.get("properties");
        if (properties != null) {
            final Object idInsight = properties.get(key);
            if (idInsight != null) {
                final LinkedHashMap propertyList = (LinkedHashMap) ((ArrayList) idInsight).get(0);
                if (propertyList != null) {
                    final String value = propertyList.get("value").toString();
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * Fetches the neighbors for a given node. Source vertex is chosen based on 'id' property and all outgoing edges are walked (limit 50)
     *
     * @param id
     * @return the neighbors
     */
    public List<Node> getNeighborsMock(final String id) {
        final List<Node> neighbors = generateNeighbors();
        return neighbors;
    }

    public static List<Node> generateNeighbors() {
        final List<Node> neighbors = new ArrayList<>();

        final Node bioNode = new Node();
        final String bioMongoId = UUID.randomUUID().toString();
        bioNode.setId(bioMongoId);
        bioNode.setLabel("Paul");
        bioNode.setType("Biographics");
        neighbors.add(bioNode);
        Node eventNode = new Node();
        final String eventMongoId = UUID.randomUUID().toString();
        eventNode.setId(eventMongoId);
        eventNode.setLabel("Bombing");
        eventNode.setType("Event");
        neighbors.add(eventNode);
        final String eqMongoId = UUID.randomUUID().toString();
        final Node eqNode = new Node();
        eqNode.setId(eqMongoId);
        eqNode.setLabel("Gun");
        eqNode.setType("Equipment");
        neighbors.add(eqNode);
        final Node rawDataNode = new Node();
        final String rawDataMongoId = UUID.randomUUID().toString();
        rawDataNode.setId(rawDataMongoId);
        rawDataNode.setLabel("Tweet");
        rawDataNode.setType("RawData");
        neighbors.add(rawDataNode);
        return neighbors;
    }


}
