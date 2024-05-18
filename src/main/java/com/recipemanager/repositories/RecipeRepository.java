package com.recipemanager.repositories;

import com.recipemanager.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends Neo4jRepository<Recipe, String> {
    List<Recipe> findAll();

    Page<Recipe> findAllByOrderByNameAsc(Pageable pageable);

    Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
