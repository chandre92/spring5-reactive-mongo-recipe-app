package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Test
    public void findAll() {
        // Arrange
        Category category = new Category();
        category.setDescription("test category");
        categoryReactiveRepository.save(category).block();

        // Act
        List<Category> result = categoryReactiveRepository.findAll().collectList().block();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(category);
    }
}