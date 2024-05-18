package com.recipemanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@JsonSerialize
@Node
public class Recipe {
    @Id
    @JsonProperty
    private String id;

    @Property("__elementId__")
    @JsonProperty("elementId")
    private Long elementId;

    @Property("name")
    @JsonProperty
    private String name;

    @Property("author")
    @JsonProperty
    private String author;

    @Property("skillLevel")
    @JsonProperty
    private String skillLevel;

    @Property("description")
    @JsonProperty
    private String description;

    @Property("cookingTime")
    @JsonProperty
    private Integer cookingTime;

    @Property("preparationTime")
    @JsonProperty
    private Integer preparationTime;

    @Property("__nodeLabels__")
    private List<String> nodeLabels;

    @JsonProperty
    @Relationship(type = "CONTAINS_INGREDIENT")
    private List<Ingredient> ingredients;
}
//recipe: {
// author: NULL,
// __elementId__: 0,
// preparationTime: 900,
// name: "Tomato & mozzarella couscous salad",
// description: "Keep some couscous in your cupboard as a stand-by, it's ready in minutes",
// id: "101233",
// skillLevel: "Easy",
// cookingTime: 0,
// __nodeLabels__: ["Recipe"]}
