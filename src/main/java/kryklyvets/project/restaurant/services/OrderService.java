package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.exceptions.OrderNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IOrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IOrderRepository repository;

    public List<Order> getAll(){
        return repository.findAll();
    }

    public Order getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order create(OrderRequest request){
        var order = Order.builder().id(new Random().nextLong()).number(request.getNumber())
                .totalPrice(request.getTotalPrice())
                .dishes(new HashSet<>()).isDelete(false)
                .dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now())
                .build();
        return  repository.save(order);
    }

    public Order update(Long id, Order newOrder){
        return repository.findById(id)
                .map(order -> {
                    order.setClient(newOrder.getClient());
                    order.setDishes(newOrder.getDishes());
                    order.setNumber(newOrder.getNumber());
                    order.setTotalPrice(newOrder.getTotalPrice());
                    order.setIsDelete(newOrder.getIsDelete());
                    order.setDateModified(LocalDateTime.now());
                    return repository.save(newOrder);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return repository.save(newOrder);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
