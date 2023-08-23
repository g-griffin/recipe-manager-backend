package de.g_griffin.recipe_manager_backend.recipe_index;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeIndexController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeIndexController.class);

    @Autowired
    private RecipeIndexService recipeIndexService;

    @GetMapping("/recipeIndices")
    ResponseEntity<List<RecipeIndex>> getAllRecipeIndices(@RequestHeader("Authorization") String bearerToken) throws JsonProcessingException {
        List<RecipeIndex> recipeIndices = recipeIndexService.getAllRecipeIndices(bearerToken);
        return ResponseEntity.status(HttpStatus.OK).body(recipeIndices);
    }

    @PostMapping("/recipeIndices")
    ResponseEntity<RecipeIndex> createRecipeIndex(@RequestHeader("Authorization") String bearerToken, @RequestBody RecipeIndex recipeIndex) throws JsonProcessingException {
        String providedUserId = recipeIndex.getUserId();

        if (recipeIndexService.isUserIdValid(providedUserId, bearerToken)) {
            RecipeIndex result = recipeIndexService.createRecipeIndex(recipeIndex);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }

        String errorMessage = "Provided userId does not match the authenticated user.";
        logger.error(errorMessage);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}