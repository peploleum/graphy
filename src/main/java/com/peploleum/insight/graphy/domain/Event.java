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
import java.time.LocalDate;
import java.util.Objects;

//import com.peploleum.insight.domain.enumeration.EventType;

/**
 * A Event.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "event")
public class Event extends DataJanus {


    @NotNull
    //@Field("event_name")
    private String eventName;

    //@Field("event_description")
    private String eventDescription;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getEventName() {
        return eventName;
    }

    public Event eventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Event eventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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
        Event event = (Event) o;
        if (event.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", eventName='" + getEventName() + "'" +
            ", eventDescription='" + getEventDescription() + "'" +
            "}";
    }
}
