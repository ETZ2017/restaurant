package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.entities.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOrder {
    List<Order> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                              @RequestParam(required = false, defaultValue = "1") Integer page);

    Order getById(@PathVariable long id);

    Order create(@RequestBody Order order);

    Order update(@PathVariable long id, @RequestBody Order order);

    void delete(@PathVariable long id);
}
