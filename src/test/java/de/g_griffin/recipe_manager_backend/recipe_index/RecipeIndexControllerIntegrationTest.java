package de.g_griffin.recipe_manager_backend.recipe_index;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipeIndexController.class)
@ExtendWith(SpringExtension.class)
public class RecipeIndexControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SecurityFilterChain securityFilterChain;

    @MockBean
    private RecipeIndexRepository recipeIndexRepository;

    @Test
    public void allRecipeIndicesAPI() throws Exception {
        List<RecipeIndex> recipeIndices = List.of(new RecipeIndex(0L, "Some recipe 12"), new RecipeIndex(1L, "Some recipe 34"));
        when(recipeIndexRepository.findAll()).thenReturn(recipeIndices);
        mvc.perform(MockMvcRequestBuilders
                        .get("/recipeIndices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].recipeIndexText").isNotEmpty());
    }

    @Test
    public void newRecipeIndexAPI() throws Exception {
        when(recipeIndexRepository.save(any())).thenReturn(new RecipeIndex(2L, "Recipe Index Text 1234"));
        mvc.perform(MockMvcRequestBuilders
                        .post("/recipeIndices")
                        .content(asJsonString(new RecipeIndex(2L, "Recipe Index Text 1234")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
