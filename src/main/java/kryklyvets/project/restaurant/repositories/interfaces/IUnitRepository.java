package kryklyvets.project.restaurant.repositories.interfaces;

import kryklyvets.project.restaurant.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitRepository extends JpaRepository<Unit, Long> {
}
