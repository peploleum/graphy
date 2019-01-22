package com.peploleum.insight.graphy.dto;

/**
 * Created by nicmir on 18/01/2019.
 */
public class Criteria {

    private String label;
    private String property;
    private String value;


    public Criteria(String label, String property, String value) {
        this.label = label;
        this.property = property;
        this.value = value;
    }

    public Criteria(){

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
