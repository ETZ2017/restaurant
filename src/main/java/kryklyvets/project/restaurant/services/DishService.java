package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.exceptions.DishNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DishService {
    @PostConstruct
    public void method() {
        DishRequest dish = new DishRequest();
        dish.setCurrency(Currency.getInstance("UAH"));
        dish.setPrice(BigDecimal.valueOf(191));

        create(dish);
    }


    private final CurrencyService currencyService;

    private final IDishRepository repository;

    public List<Dish> getAll(){
        return repository.findAll();
    }

    public Dish getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new DishNotFoundException(id));
    }

    public Dish create(DishRequest request){
        var currencyCode = request.getCurrency().getCurrencyCode();
        var rate = currencyService.getCurrency().getRates().get(currencyCode);
        var dish = Dish.builder().id(new Random().nextLong()).dish(request.getDish())
                .amount(request.getAmount()).price(request.getPrice().divide(rate, RoundingMode.HALF_UP)).currency(Currency.getInstance("EUR")).ingredients(request.getIngredients())
                .dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now())
                .build();
//        return  repository.save(dish);
        return dish;
    }

    public Dish update(Long id, DishRequest newDish){
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
                    dish.setDateCreated(LocalDateTime.now());
                    return repository.save(dish);
                })
                .orElseGet(() -> {
                    Dish dish = new Dish();
                    dish.setId(id);
                    dish.setDish(newDish.getDish());
                    dish.setIngredients(newDish.getIngredients());
                    dish.setCategory(newDish.getCategory());
                    dish.setAmount(newDish.getAmount());
                    dish.setPrice(newDish.getPrice());
                    dish.setOrders(newDish.getOrders());
                    dish.setUnit(newDish.getUnit());
                    dish.setIsDelete(newDish.getIsDelete());
                    dish.setDateModified(LocalDateTime.now());
                    return repository.save(dish);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Optional<Dish> getDishesByCategory(Long categoryId) {
        return repository.findDishesByCategory_id(categoryId);
    }
}
