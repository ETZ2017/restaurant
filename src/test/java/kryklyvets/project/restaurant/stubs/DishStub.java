package kryklyvets.project.restaurant.stubs;

import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Unit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

public class DishStub {
    public static final Long ID = 1L;

    public static Dish getRandomDish(){
        return Dish.builder().id(ID)
                .dish("Test 1")
                .category(new HashSet<>())
                .ingredients("Test 1")
                .price(new BigDecimal("123.168513"))
                .orders(new HashSet<>())
                .amount(12)
                .unit(new Unit())
                .isDelete(false)
                .dateCreated(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }

    public static DishRequest getDishRequest(){
        DishRequest dishRequest = new DishRequest();
        dishRequest.setDish("Test 1");
        dishRequest.setIngredients("Test 1");

        return dishRequest;
    }

    public static DishRequest updateRandomDish(){
        return DishRequest.builder()
                .dish("Test 2")
                .ingredients("Test 2")
                .orders(new HashSet<>())
                .build();
    }

    public static Dish getUpdatedDish(){
        return Dish.builder().id(ID)
                .dish("Test 2")
                .category(new HashSet<>())
                .ingredients("Test 2")
                .price(new BigDecimal("123.168513"))
                .orders(new HashSet<>())
                .amount(12)
                .unit(new Unit())
                .isDelete(false)
                .build();
    }
}
