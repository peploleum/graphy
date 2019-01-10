package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
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

//import com.peploleum.insight.domain.enumeration.EquipmentType;

/**
 * A Equipment.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "equipment")
public class Equipment extends DataJanus{


    //@org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    //@Mapping(mappingPath = "/mappings/asset_id_mapping.json")

    @NotNull
    //@Field("equipment_name")
    private String equipmentName;

    //@Field("equipment_description")
    private String equipmentDescription;

    //@Field("equipment_type")


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


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
