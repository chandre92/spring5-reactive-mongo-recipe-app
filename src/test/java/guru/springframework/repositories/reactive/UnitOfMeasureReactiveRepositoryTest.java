package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Test
    public void findAll() {
        // Arrange
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("test uom");
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        // Act
        List<UnitOfMeasure> result = unitOfMeasureReactiveRepository.findAll().collectList().block();

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(unitOfMeasure);
    }

    @Test
    public void findByDescription() {
        // Arrange
        String searchedDescription = "foobar";
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(searchedDescription);
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        // Act
        UnitOfMeasure result = unitOfMeasureReactiveRepository.findByDescription(searchedDescription)
                .block();

        // Assert
        assertThat(result).isEqualTo(unitOfMeasure);
    }
}