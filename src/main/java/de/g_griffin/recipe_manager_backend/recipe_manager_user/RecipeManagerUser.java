package de.g_griffin.recipe_manager_backend.recipe_manager_user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class RecipeManagerUser {

    @Id
    @Getter
    @Setter
    private String id;

    public RecipeManagerUser() {
    }

    @Override
    public String toString() {
        return "RecipeManagerUser{" + "id=" + id + '}';
    }
}
