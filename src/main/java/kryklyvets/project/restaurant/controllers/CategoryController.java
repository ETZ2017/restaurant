package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.controllers.interfaces.ICategory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController implements ICategory {
    @GetMapping
    public List<Category> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                 @RequestParam(required = false, defaultValue = "1") Integer page){
        return null;
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable long id){
        return null;
    }

    @PostMapping
    public Category create(@RequestBody Category category){
        return null;
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable long id, @RequestBody Category category){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){}
}
