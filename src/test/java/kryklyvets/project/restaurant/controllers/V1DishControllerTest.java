package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.services.ClientService;
import kryklyvets.project.restaurant.services.DishService;
import kryklyvets.project.restaurant.stubs.CategoryStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}