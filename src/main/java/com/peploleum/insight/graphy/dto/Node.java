package com.peploleum.insight.graphy.dto;

public class Node {
    private String id;
    private String mongoId;
    private String label;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + getId() +
                ", mongoId=" + getMongoId() +
                ", type='" + getType() + "'" +
                ", label='" + getLabel() + "'" +
                "}";
    }
}
