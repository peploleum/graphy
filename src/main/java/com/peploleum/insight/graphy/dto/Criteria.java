package com.peploleum.insight.graphy.dto;

/**
 * Created by nicmir on 18/01/2019.
 */
public class Criteria {

    private String property;
    private String value;

    public Criteria(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public Criteria(){

    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
