package kryklyvets.project.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.services.ClientService;
import kryklyvets.project.restaurant.services.DishService;
import kryklyvets.project.restaurant.stubs.CategoryStub;
import kryklyvets.project.restaurant.stubs.ClientStub;
import kryklyvets.project.restaurant.stubs.DishStub;
import kryklyvets.project.restaurant.stubs.OrderStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class V1DishControllerTest {
    @MockBean
    private DishService dishService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetAll() throws Exception {
        Dish dish = Dish.builder().dish("name").ingredients("ingredients").id(1L).build();
        ArrayList<Dish> list = new ArrayList<Dish>();
        Boolean add = list.add(dish);
        when(dishService.getAll()).thenReturn(list);

        mvc.perform(get("/v1/dishes")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(dish.getIngredients())));
    }

    @Test
    void testGetById() throws Exception {
        Dish dish = Dish.builder().dish("name").ingredients("ingredients").id(1L).build();
        when(dishService.getById(1L)).thenReturn(dish);

        mvc.perform(get("/v1/dishes/1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(dish.getIngredients())));
    }

    @Test
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/dishes/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void testGetDishByCatId() throws Exception {
        HashSet<Category> set = new HashSet();
        set.add(CategoryStub.getRandomCategory());

        Dish dish = Dish.builder().dish("name").category(set).ingredients("ingredients").id(1L).build();
        ArrayList<Dish> list = new ArrayList<Dish>();
        Boolean add = list.add(dish);
        when(dishService.getDishesByCategory(CategoryStub.ID)).thenReturn(Optional.of(dish));

        mvc.perform(get("/v1/dishes/1/dishes")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(dish.getIngredients())));
    }

    @Test
    public void createDish() throws Exception {
        Dish dish = DishStub.getRandomDish();
        DishRequest dishRequest = DishStub.getDishRequest();

        when(dishService.getById(OrderStub.ID)).thenReturn(DishStub.getRandomDish());
        when(dishService.create(dishRequest)).thenReturn(dish);
        mvc.perform(postRequest("/v1/dishes/create", dishRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(dish.getDish())));

    }

    @Test
    public void updateDish() throws Exception {
        Dish dish = DishStub.getRandomDish();
        DishRequest dishRequest = DishStub.getDishRequest();
        DishRequest update = DishStub.updateRandomDish();

        when(dishService.update(1L, dishRequest)).thenReturn(dish);

        when(dishService.getById(DishStub.ID)).thenReturn(dish);
        dish.setDish(update.getDish());
        when(dishService.update(DishStub.ID, update)).thenReturn(dish);
        mvc.perform(putRequest("/v1/dishes/1", update))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(update.getDish())));

    }




    private MockHttpServletRequestBuilder postRequest(String url, DishRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, DishRequest request) {
        return put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}