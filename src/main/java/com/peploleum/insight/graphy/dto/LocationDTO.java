package com.peploleum.insight.graphy.dto;

/**
 * Created by nicmir on 11/01/2019.
 */
public class LocationDTO {

    private String idMongo;
    private String name;

    public LocationDTO() {
    }

    public String getIdMongo() {
        return idMongo;
    }

    public void setIdMongo(String idMongo) {
        this.idMongo = idMongo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
