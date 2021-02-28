package kryklyvets.project.restaurant.repositories.interfaces;

import kryklyvets.project.restaurant.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
