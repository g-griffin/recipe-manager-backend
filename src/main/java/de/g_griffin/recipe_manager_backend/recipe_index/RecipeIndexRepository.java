package de.g_griffin.recipe_manager_backend.recipe_index;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIndexRepository extends JpaRepository<RecipeIndex, Long> {

    List<RecipeIndex> findByUserId(String userId);
}
