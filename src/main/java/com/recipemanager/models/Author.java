package com.recipemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Author {
    @Id
    @JsonProperty
    private String id;

    @Property("name")
    @JsonProperty
    private String name;
}
