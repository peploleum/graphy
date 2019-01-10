package com.peploleum.insight.graphy.domain;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

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



}
