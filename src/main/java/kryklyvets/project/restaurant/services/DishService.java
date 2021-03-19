package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.exceptions.DishNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DishService {
    private final IDishRepository repository;

    public List<Dish> getAll(){
        return repository.findAll();
    }

    public Dish getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
    }

    public Dish create(DishRequest request){
        var dish = Dish.builder().id(new Random().nextLong()).dish(request.getDish())
                .amount(request.getAmount()).price(request.getPrice()).ingredients(request.getIngredients())
                .dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now())
                .build();
        return  repository.save(dish);
    }

    public Dish update(Long id, Dish newDish){
        return repository.findById(id)
                .map(dish -> {
                    dish.setDish(newDish.getDish());
                    dish.setIngredients(newDish.getIngredients());
                    dish.setCategory(newDish.getCategory());
                    dish.setAmount(newDish.getAmount());
                    dish.setPrice(newDish.getPrice());
                    dish.setOrders(newDish.getOrders());
                    dish.setUnit(newDish.getUnit());
                    dish.setIsDelete(newDish.getIsDelete());
                    dish.setDateModified(LocalDateTime.now());
                    return repository.save(newDish);
                })
                .orElseGet(() -> {
                    newDish.setId(id);
                    return repository.save(newDish);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Optional<Dish> getDishesByCategory(Long categoryId) {
        return repository.findDishesByCategory_id(categoryId);
    }
}
