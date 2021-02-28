package kryklyvets.project.restaurant.repositories.interfaces;

import kryklyvets.project.restaurant.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findDishesByCategory_id(Long id);
}
