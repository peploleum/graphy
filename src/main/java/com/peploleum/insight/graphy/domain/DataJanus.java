package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import com.peploleum.insight.graphy.web.rest.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by nicmir on 10/01/2019.
 */
@Data
@Vertex
@AllArgsConstructor
@NoArgsConstructor
public class DataJanus implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String idInsight;

    private Map<String, String> properties;

    private Type type;

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void putProperties(String cle, String valeur){
        this.properties.put(cle, valeur);
    }
}
