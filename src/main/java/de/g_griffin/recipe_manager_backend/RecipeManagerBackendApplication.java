package de.g_griffin.recipe_manager_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

interface IndexRepository extends JpaRepository<Index, Long> {}

@RestController
class IndexRestController {
  @Autowired IndexRepository indexRepository;

  @GetMapping("/indices")
  Collection<Index> all() {
    return this.indexRepository.findAll();
  }

  @PostMapping("/indices")
  Index newIndex(@RequestBody Index newIndex) {
    return this.indexRepository.save(newIndex);
  }
}

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(IndexRepository repository) {

    return args -> {
      log.info(
          "Preloading "
              + repository.save(
                  new Index(
                      "Fall 2015\nPerfect Baked Potato 7\nEasiest Beef Stew 10\nShrimp Scampi 16")));
      log.info(
          "Preloading "
              + repository.save(
                  new Index(
                      "February 2016\nFluffy Dinner Rolls 3\nRoast Chicken and Winter Vegetables 5")));
    };
  }
}

@Entity
class Index {

  @Id @GeneratedValue @Getter private Long id;
  @Getter @Setter private String indexText;

  public Index() {}

  public Index(String indexText) {
    this.indexText = indexText;
  }

  @Override
  public String toString() {
    return "Index{" + "id=" + id + ", indexText='" + indexText + '\'' + '}';
  }
}
