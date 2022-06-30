package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {
    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Test
    public void findAll() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setDescription("test recipe");
        recipeReactiveRepository.save(recipe).block();

        // Act
        List<Recipe> result = recipeReactiveRepository.findAll().collectList().block();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(recipe);
    }
}