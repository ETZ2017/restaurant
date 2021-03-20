package kryklyvets.project.restaurant.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.controllers.interfaces.IUnit;
import kryklyvets.project.restaurant.services.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/units")
@Api(value = "Unit controller")
public class V1UnitController implements IUnit {
    private final UnitService service;

    @GetMapping
    @ApiResponse(code=200, message = "Successful get all")
    public List<Unit> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page){
        return service.getAll();
    }

    @ApiOperation( value = "Get by id", notes = "This method get by id")
    @GetMapping("/{id}")
    public Unit getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public Unit create(@RequestBody UnitRequest unit){
        return service.create(unit);
    }

    @PutMapping("/{id}")
    public Unit update(@PathVariable Long id, @RequestBody Unit unit){
        return service.update(id, unit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
