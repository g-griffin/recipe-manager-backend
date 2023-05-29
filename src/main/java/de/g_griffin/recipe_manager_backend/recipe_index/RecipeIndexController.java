package de.g_griffin.recipe_manager_backend.recipe_index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RecipeIndexController {
    @Autowired
    RecipeIndexRepository recipeIndexRepository;

    @GetMapping("/recipeIndices")
    Collection<RecipeIndex> all() { return this.recipeIndexRepository.findAll(); }

    @PostMapping("/recipeIndices")
    RecipeIndex newRecipeIndex(@RequestBody RecipeIndex newRecipeIndex) {
        return this.recipeIndexRepository.save(newRecipeIndex);
    }
}