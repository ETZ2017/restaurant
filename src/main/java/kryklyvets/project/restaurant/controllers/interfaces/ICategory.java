package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.entities.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICategory {
    List<Category> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                 @RequestParam(required = false, defaultValue = "1") Integer page);

    Category getById(@PathVariable Long id);

    Category create(@RequestBody CategoryRequest category);

    Category update(@PathVariable Long id, @RequestBody Category category);

    void delete(@PathVariable Long id);
}
