package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.controllers.interfaces.IOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController implements IOrder {
    @GetMapping
    public List<Order> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                              @RequestParam(required = false, defaultValue = "1") Integer page){
        return null;
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable long id){
        return null;
    }

    @PostMapping
    public Order create(@RequestBody Order order){
        return null;
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable long id, @RequestBody Order order){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){

    }
}
