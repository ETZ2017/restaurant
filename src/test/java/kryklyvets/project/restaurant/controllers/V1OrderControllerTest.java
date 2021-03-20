package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.services.DishService;
import kryklyvets.project.restaurant.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class V1OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetAll() throws Exception {
        Order order = Order.builder().number(123).id(1L).build();
        ArrayList<Order> list = new ArrayList<Order>();
        Boolean add = list.add(order);
        when(orderService.getAll()).thenReturn(list);

        mvc.perform(get("/v1/orders")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(valueOf(order.getNumber()))));
    }

    @Test
    void testGetById() throws Exception {
        Order order = Order.builder().number(123).id(1L).build();
        when(orderService.getById(1L)).thenReturn(order);

        mvc.perform(get("/v1/orders/1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(valueOf(order.getNumber()))));
    }

    @Test
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/orders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}