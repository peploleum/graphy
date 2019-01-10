package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
/*import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;*/

import java.util.Objects;

//import com.peploleum.insight.domain.enumeration.Gender;

/**
 * A Biographics.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
public class Biographics extends DataJanus{

    //@org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    //@Mapping(mappingPath = "/mappings/asset_id_mapping.json")
    //@Field("biographics_name")
    private String biographicsName;

    //@Field("biographics_age")
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


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
