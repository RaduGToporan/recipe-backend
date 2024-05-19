package com.recipemanager.repositories;

import com.recipemanager.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends Neo4jRepository<Recipe, String> {
    List<Recipe> findAll();

    Page<Recipe> findAllByOrderByNameAsc(Pageable pageable);

    Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "MATCH (r:Recipe) WHERE ALL(ingName IN $ingredientNames WHERE EXISTS ((r)-[:CONTAINS_INGREDIENT]->(:Ingredient {name: ingName}))) RETURN r ORDER BY r.name ASC",
            countQuery = "MATCH (r:Recipe) WHERE ALL(ingName IN $ingredientNames WHERE EXISTS ((r)-[:CONTAINS_INGREDIENT]->(:Ingredient {name: ingName}))) RETURN count(r)")
    Page<Recipe> findByIngredients(List<String> ingredientNames, Pageable pageable);

    @Query(value = "MATCH (r:Recipe) WHERE r.name =~ ('.*' + $name + '.*') AND ALL(ingName IN $ingredientNames WHERE EXISTS ((r)-[:CONTAINS_INGREDIENT]->(:Ingredient {name: ingName}))) RETURN r ORDER BY r.name ASC",
            countQuery = "MATCH (r:Recipe) WHERE r.name =~ ('.*' + $name + '.*') AND ALL(ingName IN $ingredientNames WHERE EXISTS ((r)-[:CONTAINS_INGREDIENT]->(:Ingredient {name: ingName}))) RETURN count(r)")
    Page<Recipe> findByIngredientsAndName(String name, List<String> ingredientNames, Pageable pageable);

    @Query(value = "MATCH (a:Author)-[:WROTE]->(r:Recipe) WHERE a.name = $authorName RETURN r",
            countQuery = "MATCH (a:Author)-[:WROTE]->(r:Recipe) WHERE a.name = $authorName RETURN count(r)")
    Page<Recipe> findByAuthorName(String authorName, Pageable pageable);
}
