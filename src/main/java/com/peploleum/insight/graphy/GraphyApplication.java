package com.peploleum.insight.graphy;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import com.peploleum.insight.graphy.domain.Network;
import com.peploleum.insight.graphy.domain.Person;
import com.peploleum.insight.graphy.domain.Relation;
import com.peploleum.insight.graphy.repository.BiographicsRepository;
import com.peploleum.insight.graphy.repository.NetworkRepository;
import com.peploleum.insight.graphy.repository.PersonRepository;
import com.peploleum.insight.graphy.repository.RelationRepository;
import com.peploleum.insight.graphy.service.BiographicsServiceImpl;
import com.peploleum.insight.graphy.service.RelationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    @Autowired
    private BiographicsServiceImpl biographicsService;

    @Autowired
    private RelationServiceImpl relationService;

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
        /*for (int i = 0; i < VERTICES_THRESHOLD; i++) {
            final Person person = new Person();
            person.setName(UUID.randomUUID().toString());
            final int randomThreshold = ThreadLocalRandom.current().nextInt(0, AGE_THRESHOLD);
            person.setAge(String.valueOf(randomThreshold));
            final Person savedPerson = this.personRepo.save(person);
            this.log.info("Vertex saved: " + savedPerson.getId());
            savedPersons.add(savedPerson);
        }*/

        /*log.info("Creating Biographics 1");
        Long idBio = biographicsService.save("Vincent Dautreme", UUID.randomUUID().toString());
        log.info("Biographics created : "+biographicsService.findOne(idBio).toString());

        log.info("Deleting Biographics... ");
        log.info("is Biographics exists ? "+biographicsService.findOne(idBio).toString());


        log.info("Creating Biographics 2");
        Long idBio2 = biographicsService.save("Damien Bonnal", UUID.randomUUID().toString());
        log.info("Biographics created : "+biographicsService.findOne(idBio2).toString());

        log.info("Creating Relation between 1 and 2");
        String idRelation = relationService.save(idBio,idBio2,"connait", Type.Biographics, Type.Biographics);

        log.info("Relation created : ");*/


        /*log.info("Creating edges");

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
        }*/

        //log.info("Creating graph");
        //log.info("1234 exists or not ? " + this.networkRepo.findAll());
        //this.networkRepo.existsById(Long.valueOf("1234")
        //if (this.network == null) {
        //    this.log.info("Creating new graph");
        //    this.network = new Network();
        //} else {
            //this.log.info("1234 exists ? " + this.networkRepo.findById(Long.valueOf("1234")).isPresent());
            //this.network = this.networkRepo.findById(Long.valueOf("1234")).get();
        //    this.log.info("Using existing graph: " + this.network.getId());
        //}
        /*this.network.getVertexes().addAll(savedPersons);
        this.network.getEdges().addAll(savedRelations);
        final Network saved = this.networkRepo.save(this.network);
        saved.setId("1234");
        log.info("Saved graph " + saved.getId() + " " + saved.getEdges().size() + " " + saved.getVertexes().size());
        log.info(String.valueOf(saved.getVertexes()));
        extract(savedPersons);*/


    }

    private void extract(Set<Person> savedPersons) {
        List<Long> collect = savedPersons.stream().map(p -> {
            return Long.valueOf(p.getId());
        }).collect(Collectors.toList());
        Iterable<Person> allById = this.personRepo.findAllById(collect);
        log.info("allById = "+ allById);
    }



}

