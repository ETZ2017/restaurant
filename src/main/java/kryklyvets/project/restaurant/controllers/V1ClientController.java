package kryklyvets.project.restaurant.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.controllers.interfaces.IClient;
import kryklyvets.project.restaurant.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clients")
@Api(value = "Client controller")
public class V1ClientController implements IClient {
    private final ClientService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ApiResponse(code=200, message = "Successful get all")
    public List<Client> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false, defaultValue = "1") Integer page){
        return service.getAll();
    }

    @ApiOperation( value = "Get by id", notes = "This method get by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Client getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client create(@RequestBody ClientRequest client){
        return service.create(client);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client update(@PathVariable Long id, @RequestBody ClientRequest client){
        return service.update(id, client);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
