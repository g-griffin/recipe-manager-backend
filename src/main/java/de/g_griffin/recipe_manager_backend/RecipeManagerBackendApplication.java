package de.g_griffin.recipe_manager_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.g_griffin.recipe_manager_backend")
public class RecipeManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeManagerBackendApplication.class, args);
    }
}
