package kryklyvets.project.restaurant.stubs;

import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.entities.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

public class OrderStub {
    public static final Long ID = 1L;

    public static Order getRandomOrder(){
        return Order.builder().id(ID)
                .number(1)
                .dishes(new HashSet<>())
                .client(new Client())
                .totalPrice(new BigDecimal("12.000000235"))
                .isDelete(false)
                .dateCreated(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }

    public static OrderRequest getOrderRequest(){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setNumber(1);

        return orderRequest;
    }

    public static Order updateRandomOrder(){
        return Order.builder().id(ID)
                .number(2)
                .dishes(new HashSet<>())
                .build();
    }
}
