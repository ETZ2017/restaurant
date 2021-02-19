package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.entities.Unit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUnit {
    List<Unit> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                             @RequestParam(required = false, defaultValue = "1") Integer page);

    Unit getById(@PathVariable long id);

    Unit create(@RequestBody Unit unit);

    Unit update(@PathVariable long id, @RequestBody Unit unit);

    void delete(@PathVariable long id);
}
