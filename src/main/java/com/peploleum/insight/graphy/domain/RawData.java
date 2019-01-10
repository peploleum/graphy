package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import groovy.transform.Field;
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
import java.time.LocalDate;
import java.util.Objects;

/**
 * A RawData.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "raw_data")
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "rawdata")
public class RawData extends DataJanus {

    @NotNull
    //@Field("raw_data_name")
    private String rawDataName;

    //@Field("raw_data_type")
    private String rawDataType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getRawDataName() {
        return rawDataName;
    }

    public RawData rawDataName(String rawDataName) {
        this.rawDataName = rawDataName;
        return this;
    }

    public void setRawDataName(String rawDataName) {
        this.rawDataName = rawDataName;
    }

    public String getRawDataType() {
        return rawDataType;
    }

    public RawData rawDataType(String rawDataType) {
        this.rawDataType = rawDataType;
        return this;
    }

    public void setRawDataType(String rawDataType) {
        this.rawDataType = rawDataType;
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
        RawData rawData = (RawData) o;
        if (rawData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rawData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RawData{" +
            "id=" + getId() +
            ", rawDataName='" + getRawDataName() + "'" +
            ", rawDataType='" + getRawDataType() + "'" +
            "}";
    }
}
