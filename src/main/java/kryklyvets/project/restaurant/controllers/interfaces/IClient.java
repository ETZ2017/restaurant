package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IClient {
    List<Client> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false, defaultValue = "1") Integer page);

    Client getById(@PathVariable Long id);

    Client create(@RequestBody ClientRequest client);

    Client update(@PathVariable Long id, @RequestBody ClientRequest client);

    void delete(@PathVariable Long id);
}
