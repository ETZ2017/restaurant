package kryklyvets.project.restaurant.repositories.interfaces;

import kryklyvets.project.restaurant.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
}
