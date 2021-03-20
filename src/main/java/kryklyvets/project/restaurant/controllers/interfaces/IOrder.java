package kryklyvets.project.restaurant.controllers.interfaces;

import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOrder {
    List<Order> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                              @RequestParam(required = false, defaultValue = "1") Integer page);

    Order getById(@PathVariable Long id);

    Order create(@RequestBody OrderRequest order);

    Order update(@PathVariable Long id, @RequestBody OrderRequest order);

    void delete(@PathVariable Long id);
}
