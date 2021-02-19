package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.controllers.interfaces.IDish;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController implements IDish {
    @GetMapping
    public List<Dish> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page){
        return null;
    }

    @GetMapping("/{id}")
    public Dish getById(@PathVariable long id){
        return null;
    }

    @PostMapping
    public Dish create(@RequestBody Dish dish){
        return null;
    }

    @PutMapping("/{id}")
    public Dish update(@PathVariable long id, @RequestBody Dish dish){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){

    }
}
