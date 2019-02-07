package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tinkerpop.shaded.kryo.NotNull;

import java.util.Objects;


/**
 * A RawData.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
public class RawData extends DataJanus {

    @NotNull
    private String rawDataName;

    private String rawDataType;


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
