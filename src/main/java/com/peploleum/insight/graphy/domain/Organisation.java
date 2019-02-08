package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tinkerpop.shaded.kryo.NotNull;

import java.util.Objects;


/**
 * A Organisation.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
public class Organisation extends DataJanus {


    @NotNull
    private String organisationName;

    private String organisationDescrption;

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
