package kryklyvets.project.restaurant.services.interfaces;

import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findDishesByCategory_id(Long id);

    @Query(value = "SELECT d.id, d.dish FROM Dish as d where d.dish = :dish ", nativeQuery = true)
    List<Dish> findByName(String dish);
}
