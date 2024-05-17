package com.recipemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@JsonSerialize
@Node
public class Ingredient {
    @Id
    private String id;

    @Property("__elementId__")  // Mapping to the elementId in the database if needed
    @JsonProperty("elementId")
    private Long elementId;

    @Property("name")
    @JsonProperty
    private String name;
}
