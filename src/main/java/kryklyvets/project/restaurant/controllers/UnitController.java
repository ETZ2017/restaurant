package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.controllers.interfaces.IUnit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController implements IUnit {

    @GetMapping
    public List<Unit> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                   @RequestParam(required = false, defaultValue = "1") Integer page){
        return null;
    }

    @GetMapping("/{id}")
    public Unit getById(@PathVariable long id){
        return null;
    }

    @PostMapping
    public Unit create(@RequestBody Unit unit){
        return null;
    }

    @PutMapping("/{id}")
    public Unit update(@PathVariable long id, @RequestBody Unit unit){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){

    }
}
