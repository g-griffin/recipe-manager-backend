package de.g_griffin.recipe_manager_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@SpringBootApplication
public class RecipeManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeManagerBackendApplication.class, args);
    }
}

interface RecipeIndexRepository extends JpaRepository<RecipeIndex, Long> {
}

@RestController
class RecipeIndexRestController {
    @Autowired
    RecipeIndexRepository recipeIndexRepository;

    @GetMapping("/recipeIndices")
    Collection<RecipeIndex> all() {
        return this.recipeIndexRepository.findAll();
    }

    @PostMapping("/recipeIndices")
    RecipeIndex newRecipeIndex(@RequestBody RecipeIndex newRecipeIndex) {
        return this.recipeIndexRepository.save(newRecipeIndex);
    }
}

@Entity
class RecipeIndex {

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

    @Override
    public String toString() {
        return "RecipeIndex{" + "id=" + id + ", indexText='" + recipeIndexText + '\'' + '}';
    }
}
