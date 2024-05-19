package com.recipemanager.repositories;

import com.recipemanager.models.Ingredient;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface IngredientRepository extends Neo4jRepository<Ingredient, String> {
    @Query("MATCH (i:Ingredient) WHERE i.name =~ ('(?i).*' + $name + '.*') RETURN i.name")
    List<String> findByNameContainingIgnoreCase(String name);
}
