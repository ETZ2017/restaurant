package kryklyvets.project.restaurant.repositories;

import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.services.interfaces.IDishRepository;
import kryklyvets.project.restaurant.services.interfaces.IUnitRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DishRepositoryTest {
    @Autowired
    private IDishRepository dishRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindDishByName() {
        Dish expectedDish = Dish.builder()
                            .amount(123)
                            .dateCreated(LocalDateTime.now())
                            .dateModified(LocalDateTime.now())
                            .dish("Test")
                            .ingredients("Qwerty")
                            .isDelete(false)
                            .price(new BigDecimal(12.3))
                            .id(1L)
                            .build();
        entityManager.persist(expectedDish);
        entityManager.flush();

        List<Dish> actualDish = dishRepository.findByName("Test");

        Assertions.assertThat(actualDish.get(0)).isEqualTo(expectedDish);
    }
}
