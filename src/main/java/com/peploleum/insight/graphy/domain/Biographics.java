package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
public class Biographics extends DataJanus {


    private String biographicsName;

    private Integer biographicsAge;


    public String getBiographicsName() {
        return biographicsName;
    }

    public Biographics biographicsName(String biographicsName) {
        this.biographicsName = biographicsName;
        return this;
    }

    public void setBiographicsName(String biographicsName) {
        this.biographicsName = biographicsName;
    }

    public Integer getBiographicsAge() {
        return biographicsAge;
    }

    public Biographics biographicsAge(Integer biographicsAge) {
        this.biographicsAge = biographicsAge;
        return this;
    }

    public void setBiographicsAge(Integer biographicsAge) {
        this.biographicsAge = biographicsAge;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Biographics biographics = (Biographics) o;
        if (biographics.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), biographics.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Biographics{" +
                "id=" + getId() +
                ", biographicsName='" + getBiographicsName() + "'" +
                ", biographicsAge=" + getBiographicsAge() +
                "}";
    }
}
