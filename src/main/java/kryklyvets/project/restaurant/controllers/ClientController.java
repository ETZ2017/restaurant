package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.controllers.interfaces.IClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController implements IClient {
    @GetMapping
    public List<Client> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false, defaultValue = "1") Integer page){
        return null;
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable long id){
        return null;
    }

    @PostMapping
    public Client create(@RequestBody Client client){
        return null;
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable long id, @RequestBody Client client){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){

    }
}
