package kryklyvets.project.restaurant.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.controllers.interfaces.IDish;
import kryklyvets.project.restaurant.repositories.DishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/dishes")
@Api(value = "Dish controller")
public class V1DishController implements IDish {
    private final DishService service;

    @GetMapping
    @ApiResponse(code=200, message = "Successful get all")
    public List<Dish> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page){
        return service.getAll();
    }

    @ApiOperation( value = "Get by id", notes = "This method get by id")
    @GetMapping("/{id}")
    public Dish getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public Dish create(@RequestBody DishRequest dish){
        return service.create(dish);
    }

    @PutMapping("/{id}")
    public Dish update(@PathVariable Long id, @RequestBody Dish dish){
        return service.update(id, dish);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/{categoryId}/dishes")
    public Optional<Dish> getDishByCategory(@PathVariable Long categoryId) {
        return service.getDishesByCategory(categoryId);
    }
}
