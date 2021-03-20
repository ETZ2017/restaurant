package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Unit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUnit {
    List<Unit> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                      @RequestParam(required = false, defaultValue = "1") Integer page);

    Unit getById(@PathVariable Long id);

    Unit create(@RequestBody UnitRequest unit);

    Unit update(@PathVariable Long id, @RequestBody UnitRequest unit);

    void delete(@PathVariable Long id);
}
