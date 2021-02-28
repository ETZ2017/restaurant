package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.Dish;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IDish {
    List<Dish> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page);

    Dish getById(@PathVariable Long id);

    Dish create(@RequestBody DishRequest dish);

    Dish update(@PathVariable Long id, @RequestBody Dish dish);

    void delete(@PathVariable Long id);
}
