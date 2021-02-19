package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.entities.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IClient {
    List<Client> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false, defaultValue = "1") Integer page);

    Client getById(@PathVariable long id);

    Client create(@RequestBody Client client);

    Client update(@PathVariable long id, @RequestBody Client client);

    void delete(@PathVariable long id);
}
