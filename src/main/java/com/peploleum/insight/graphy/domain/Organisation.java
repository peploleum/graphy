package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import javafx.scene.chart.PieChart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tinkerpop.shaded.kryo.NotNull;
import org.springframework.data.annotation.Id;
/*import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;*/

import java.io.Serializable;
import java.util.Objects;

//import com.peploleum.insight.domain.enumeration.Size;

/**
 * A Organisation.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "organisation")
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "organisation")
public class Organisation extends DataJanus {


    @NotNull
    //@Field("organisation_name")
    private String organisationName;

    //@Field("organisation_descrption")
    private String organisationDescrption;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getOrganisationName() {
        return organisationName;
    }

    public Organisation organisationName(String organisationName) {
        this.organisationName = organisationName;
        return this;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisationDescrption() {
        return organisationDescrption;
    }

    public Organisation organisationDescrption(String organisationDescrption) {
        this.organisationDescrption = organisationDescrption;
        return this;
    }

    public void setOrganisationDescrption(String organisationDescrption) {
        this.organisationDescrption = organisationDescrption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Organisation organisation = (Organisation) o;
        if (organisation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Organisation{" +
            "id=" + getId() +
            ", organisationName='" + getOrganisationName() + "'" +
            ", organisationDescrption='" + getOrganisationDescrption() + "'" +
            "}";
    }
}
