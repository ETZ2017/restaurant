package kryklyvets.project.restaurant.services;
import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.exceptions.OrderNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IOrderRepository;
import kryklyvets.project.restaurant.stubs.OrderStub;
import kryklyvets.project.restaurant.stubs.UnitStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class OrderServiceTest {
    private OrderService service;
    @Mock
    private IOrderRepository orderRepository;
    public static final Long ID = 1L;

    @BeforeEach
    void setup(){
        service = new OrderService(orderRepository);
    }

    @Test
    void testSuccessfulGetById() {
        Order order = OrderStub.getRandomOrder();

        Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(order));
        Order result = service.getById(OrderStub.ID);

        assertAll(
                () -> assertEquals(result.getId(), order.getId()),
                () -> assertEquals(result.getNumber(), order.getNumber()),
                () -> assertEquals(result.getDishes().size(), order.getDishes().size()),
                () -> assertEquals(result.getTotalPrice(), order.getTotalPrice()),
                () -> assertEquals(result.getClient(), order.getClient()),
                () -> assertEquals(result.getIsDelete(), order.getIsDelete()),
                () -> assertEquals(result.getDateCreated(), order.getDateCreated()),
                () -> assertEquals(result.getDateModified(), order.getDateModified())
        );
    }

    @Test
    void testNotSuccessfulGetById() {
        Mockito.when(orderRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(OrderNotFoundException.class, () -> service.getById(OrderStub.ID));

        assertEquals(e.getMessage(), "Could not find order " + OrderStub.ID);
    }

    @Test
    void testSuccessfulCreate() {
        var captor = ArgumentCaptor.forClass(Order.class);
        Order order = OrderStub.getRandomOrder();

        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(OrderStub.getRandomOrder());

        Order result = service.create(OrderStub.getOrderRequest());

        Mockito.verify(orderRepository, Mockito.atLeastOnce()).save(captor.capture());

//        assertEquals(client, captor.getValue());
        assertAll(
                () -> assertEquals(result.getNumber(), order.getNumber())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(OrderStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(orderRepository, Mockito.atLeastOnce()).deleteById(captor.capture());

        assertEquals(OrderStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulUpdate() {
        OrderRequest order = OrderStub.updateRandomOrder();

        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(OrderStub.getUpdatedOrder());

        Order result = service.update(OrderStub.ID, OrderStub.updateRandomOrder());

        assertEquals(order.getNumber(), result.getNumber());
    }

    @Test
    void testSuccessfulGetAll() {
        Order order = OrderStub.getRandomOrder();

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order));
        List<Order> result = service.getAll();

        assertEquals(order.getNumber(), result.get(0).getNumber());
    }
}