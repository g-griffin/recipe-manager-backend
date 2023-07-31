package de.g_griffin.recipe_manager_backend.recipe_index;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class RecipeIndex {

    @Id
    @GeneratedValue
    @Getter
    private Long id;
    @Getter
    @Setter
    private String recipeIndexText;

    public RecipeIndex() {
    }

    public RecipeIndex(String recipeIndexText) {
        this.recipeIndexText = recipeIndexText;
    }

    @Deprecated  // Deprecated constructor for testing purposes
    public RecipeIndex(Long id, String recipeIndexText) {
        this.id = id;
        this.recipeIndexText = recipeIndexText;
    }

    @Override
    public String toString() {
        return "RecipeIndex{" + "id=" + id + ", recipeIndexText='" + recipeIndexText + '\'' + '}';
    }
}