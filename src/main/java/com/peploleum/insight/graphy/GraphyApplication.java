package com.peploleum.insight.graphy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GraphyApplication {

    private static final Logger log = LoggerFactory.getLogger(GraphyApplication.class);

    public static void main(String[] args) {
        log.warn("Starting app");
        SpringApplication.run(GraphyApplication.class, args);
    }

    @PostConstruct
    public void setup() {
        log.warn("Started");

    }
}

