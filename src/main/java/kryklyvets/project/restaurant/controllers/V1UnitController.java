package kryklyvets.project.restaurant.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.controllers.interfaces.IUnit;
import kryklyvets.project.restaurant.services.UnitService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/units")
@Api(value = "Unit controller")
public class V1UnitController implements IUnit {
    private final UnitService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ApiResponse(code=200, message = "Successful get all")
    public List<Unit> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page){
        return service.getAll();
    }

    @ApiOperation( value = "Get by id", notes = "This method get by id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Unit getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Unit create(@RequestBody UnitRequest unit){
        return service.create(unit);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Unit update(@PathVariable Long id, @RequestBody UnitRequest unit){
        return service.update(id, unit);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
