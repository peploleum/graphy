package com.peploleum.insight.graphy.dto;

/**
 * Created by nicmir on 11/01/2019.
 */
public class EquipmentDTO {
    private String idMongo;
    private String name;
    private String description;

    public EquipmentDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
