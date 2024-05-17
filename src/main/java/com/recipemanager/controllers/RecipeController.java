package com.recipemanager.controllers;

import com.recipemanager.models.Recipe;
import com.recipemanager.repositories.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/testdb")
    public Object testDbConnection() {
        try {
            return recipeRepository.findById("98604");
            //return "Database connection!";
        } catch (Exception e) {
            return "Failed to connect to database: " + e.getMessage();
        }
    }

    @GetMapping("/recipes")
    public Page<Recipe> getAllRecipes(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return recipeRepository.findAllByOrderByNameAsc(PageRequest.of(page, size));
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(@PathVariable String id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
