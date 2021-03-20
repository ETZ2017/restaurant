package kryklyvets.project.restaurant.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.controllers.interfaces.IOrder;
import kryklyvets.project.restaurant.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/orders")
@Api(value = "Order controller")
public class V1OrderController implements IOrder {
    private final OrderService service;

    @GetMapping
    @ApiResponse(code=200, message = "Successful get all")
    public List<Order> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                              @RequestParam(required = false, defaultValue = "1") Integer page){
        return service.getAll();
    }

    @ApiOperation( value = "Get by id", notes = "This method get by id")
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public Order create(@RequestBody OrderRequest order){
        return service.create(order);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderRequest order){
        return service.update(id, order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
