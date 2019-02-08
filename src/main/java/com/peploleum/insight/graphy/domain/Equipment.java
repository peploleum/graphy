package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tinkerpop.shaded.kryo.NotNull;

import java.util.Objects;

/**
 * A Equipment.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
public class Equipment extends DataJanus{


    @NotNull
    private String equipmentName;

    private String equipmentDescription;


    public String getEquipmentName() {
        return equipmentName;
    }

    public Equipment equipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
        return this;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public Equipment equipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
        return this;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
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
        Equipment equipment = (Equipment) o;
        if (equipment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), equipment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Equipment{" +
            "id=" + getId() +
            ", equipmentName='" + getEquipmentName() + "'" +
            ", equipmentDescription='" + getEquipmentDescription() + "'" +
            "}";
    }
}
