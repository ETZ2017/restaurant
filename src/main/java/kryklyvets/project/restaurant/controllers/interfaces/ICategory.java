package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.entities.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICategory {
    List<Category> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                 @RequestParam(required = false, defaultValue = "1") Integer page);

    Category getById(@PathVariable long id);

    Category create(@RequestBody Category category);

    Category update(@PathVariable long id, @RequestBody Category category);

    void delete(@PathVariable long id);
}
