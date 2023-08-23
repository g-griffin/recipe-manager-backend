package de.g_griffin.recipe_manager_backend.recipe_index;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.g_griffin.recipe_manager_backend.user_info.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIndexService {

    @Autowired
    private RecipeIndexRepository recipeIndexRepository;

    @Autowired
    private UserInfoService userInfoService;

    public List<RecipeIndex> getAllRecipeIndices(String bearerToken) throws JsonProcessingException {
        String userIdFromUserInfo = userInfoService.fetchSubFromUserInfoEndpoint(bearerToken);
        return recipeIndexRepository.findByUserId(userIdFromUserInfo);
    }

    public RecipeIndex createRecipeIndex(RecipeIndex recipeIndex) {
        return recipeIndexRepository.save(recipeIndex);
    }

    public boolean isUserIdValid(String providedUserId, String bearerToken) throws JsonProcessingException {
        String userIdFromUserInfo = userInfoService.fetchSubFromUserInfoEndpoint(bearerToken);
        return userIdFromUserInfo.equals(providedUserId);
    }
}
