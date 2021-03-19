package kryklyvets.project.restaurant.services.interfaces;

import kryklyvets.project.restaurant.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
