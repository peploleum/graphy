package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.EdgeFrom;
import com.microsoft.spring.data.gremlin.annotation.EdgeTo;
import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import org.springframework.data.annotation.Id;

/**
 * Created by nicmir on 10/01/2019.
 */
public class RelationBiographics {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    @EdgeFrom
    private Biographics objectFrom;

    @EdgeTo
    private Biographics objectTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Biographics getObjectFrom() {
        return objectFrom;
    }

    public void setObjectFrom(Biographics objectFrom) {
        this.objectFrom = objectFrom;
    }

    public Biographics getObjectTo() {
        return objectTo;
    }

    public void setObjectTo(Biographics objectTo) {
        this.objectTo = objectTo;
    }
}
