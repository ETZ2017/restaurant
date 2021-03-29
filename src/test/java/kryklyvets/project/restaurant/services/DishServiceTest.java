package kryklyvets.project.restaurant.services;
import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.*;
import kryklyvets.project.restaurant.exceptions.ClientNotFoundException;
import kryklyvets.project.restaurant.exceptions.DishNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IDishRepository;
import kryklyvets.project.restaurant.stubs.*;
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
class DishServiceTest {
    private DishService service;
    @Mock
    private IDishRepository dishRepository;
    public static final Long ID = 1L;

    @BeforeEach
    void setup(){
        service = new DishService(dishRepository);
    }

    @Test
    void testSuccessfulGetById() {
        Dish dish = DishStub.getRandomDish();

        Mockito.when(dishRepository.findById(Mockito.any())).thenReturn(Optional.of(dish));
        Dish result = service.getById(DishStub.ID);

        assertAll(
                () -> assertEquals(result.getId(), dish.getId()),
                () -> assertEquals(result.getDish(), dish.getDish()),
                () -> assertEquals(result.getIngredients(), dish.getIngredients()),
                () -> assertEquals(result.getOrders().size(), dish.getOrders().size()),
                () -> assertEquals(result.getCategory(), dish.getCategory()),
                () -> assertEquals(result.getAmount(), dish.getAmount()),
                () -> assertEquals(result.getUnit(), dish.getUnit()),
                () -> assertEquals(result.getPrice(), dish.getPrice()),
                () -> assertEquals(result.getIsDelete(), dish.getIsDelete()),
                () -> assertEquals(result.getDateCreated(), dish.getDateCreated()),
                () -> assertEquals(result.getDateModified(), dish.getDateModified())
        );
    }

    @Test
    void testNotSuccessfulGetById() {
        Mockito.when(dishRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(DishNotFoundException.class, () -> service.getById(DishStub.ID));

        assertEquals(e.getMessage(), "Could not find dish " + DishStub.ID);
    }

    @Test
    void testSuccessfulCreate() {
        var captor = ArgumentCaptor.forClass(Dish.class);
        Dish dish = DishStub.getRandomDish();

        Mockito.when(dishRepository.save(Mockito.any())).thenReturn(DishStub.getRandomDish());

        Dish result = service.create(DishStub.getDishRequest());

        Mockito.verify(dishRepository, Mockito.atLeastOnce()).save(captor.capture());

//        assertEquals(client, captor.getValue());
        assertAll(
                () -> assertEquals(result.getDish(), dish.getDish()),
                () -> assertEquals(result.getIngredients(), dish.getIngredients())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(DishStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(dishRepository, Mockito.atLeastOnce()).deleteById(captor.capture());

        assertEquals(DishStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulUpdate() {
        DishRequest dish = DishStub.updateRandomDish();

        Mockito.when(dishRepository.save(Mockito.any())).thenReturn(DishStub.getUpdatedDish());

        Dish result = service.update(DishStub.ID, DishStub.updateRandomDish());

        assertAll(
                () -> assertEquals(dish.getDish(), result.getDish()),
                () -> assertEquals(dish.getIngredients(), result.getIngredients())
        );
    }

    @Test
    void testSuccessfulGetAll() {
        Dish dish = DishStub.getRandomDish();

        Mockito.when(dishRepository.findAll()).thenReturn(List.of(dish));
        List<Dish> result = service.getAll();

        assertEquals(dish.getDish(), result.get(0).getDish());
    }
}