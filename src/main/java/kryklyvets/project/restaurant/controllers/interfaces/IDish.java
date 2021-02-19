package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.entities.Dish;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IDish {
    List<Dish> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page);

    Dish getById(@PathVariable long id);

    Dish create(@RequestBody Dish dish);

    Dish update(@PathVariable long id, @RequestBody Dish dish);

    void delete(@PathVariable long id);
}
