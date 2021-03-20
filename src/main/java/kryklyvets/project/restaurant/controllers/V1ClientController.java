package kryklyvets.project.restaurant.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.controllers.interfaces.IClient;
import kryklyvets.project.restaurant.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/clients")
@Api(value = "Client controller")
public class V1ClientController implements IClient {
    private final ClientService service;

    @GetMapping
    @ApiResponse(code=200, message = "Successful get all")
    public List<Client> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false, defaultValue = "1") Integer page){
        return service.getAll();
    }

    @ApiOperation( value = "Get by id", notes = "This method get by id")
    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public Client create(@RequestBody ClientRequest client){
        return service.create(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody ClientRequest client){
        return service.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
