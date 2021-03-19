package kryklyvets.project.restaurant.services.interfaces;

import kryklyvets.project.restaurant.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUnitRepository extends JpaRepository<Unit, Long> {

}
