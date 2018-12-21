package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.peploleum.insight.graphy.domain.Network;
import com.peploleum.insight.graphy.domain.Person;
import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.repository.NetworkRepository;
import com.peploleum.insight.graphy.repository.PersonRepository;
import com.peploleum.insight.graphy.repository.RelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class GraphyApplication {
    private static final int AGE_THRESHOLD = 100;

    @Value("${vertex-threshold}")
    private int vertexThreshod;
    private Network network = null;

    private static final Logger log = LoggerFactory.getLogger(GraphyApplication.class);

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private RelationRepository relationRepo;

    @Autowired
    private NetworkRepository networkRepo;

    @Autowired
    private GremlinFactory factory;

    public static void main(String[] args) {

        log.warn("Starting app");
        SpringApplication.run(GraphyApplication.class, args);
        log.warn("app running");
    }

    @PostConstruct
    public void setup() {
        log.warn("Starting");
//        try {
//            final Iterable<Network> all = this.networkRepo.findAll(Network.class);
//            if (all.iterator().hasNext()) {
//                this.log.info("Found network");
//                this.network = all.iterator().next();
//            }
//        } catch (Exception e) {
//            this.log.error(e.getMessage(), e);
//        }
        log.info("Creating vertices");

        final int VERTICES_THRESHOLD = this.vertexThreshod;

        final Set<Person> savedPersons = new HashSet<>();
        for (int i = 0; i < VERTICES_THRESHOLD; i++) {
            final Person person = new Person();
            person.setName(UUID.randomUUID().toString());
            final int randomThreshold = ThreadLocalRandom.current().nextInt(0, AGE_THRESHOLD);
            person.setAge(String.valueOf(randomThreshold));
            final Person savedPerson = this.personRepo.save(person);
            this.log.info("Vertex saved: " + savedPerson.getId());
            savedPersons.add(savedPerson);
        }
        log.info("Creating edges");

        final Set<Relation> savedRelations = new HashSet<Relation>();
        for (final Person savedPerson : savedPersons) {
            for (final Person savedPerson1 : savedPersons) {
                final Relation relation = new Relation();
                relation.setName("linkedTo");
                relation.setPersonFrom(savedPerson);
                relation.setPersonTo(savedPerson1);
                final Relation savedRelation = this.relationRepo.save(relation);
                this.log.info("Created edge: " + savedRelation.getId());
                savedRelations.add(savedRelation);
            }
        }

        log.info("Creating graph");
        if (this.network == null) {
            this.log.info("Creating new graph");
            this.network = new Network();
        } else {
            this.log.info("Using existing graph: " + this.network.getId());
        }
        this.network.getVertexes().addAll(savedPersons);
        this.network.getEdges().addAll(savedRelations);
        final Network saved = this.networkRepo.save(this.network);
        log.info("Saved graph " + saved.getId() + " " + saved.getEdges().size() + " " + saved.getVertexes().size());
    }
}

