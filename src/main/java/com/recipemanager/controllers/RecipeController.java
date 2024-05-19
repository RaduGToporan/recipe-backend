package com.recipemanager.controllers;

import com.recipemanager.models.Recipe;
import com.recipemanager.repositories.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "ingredients", required = false) List<String> ingredients
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (name != null && !name.isEmpty() && ingredients != null && !ingredients.isEmpty()) {
            return recipeRepository.findByIngredientsAndName(name, ingredients, pageRequest);
        } else if (ingredients != null && !ingredients.isEmpty()) {
            return recipeRepository.findByIngredients(ingredients, pageRequest);
        } else if (name != null && !name.isEmpty()) {
            return recipeRepository.findByNameContainingIgnoreCase(name, pageRequest);
        } else {
            return recipeRepository.findAllByOrderByNameAsc(pageRequest);
        }
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(@PathVariable String id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
