package kryklyvets.project.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.services.DishService;
import kryklyvets.project.restaurant.services.OrderService;
import kryklyvets.project.restaurant.stubs.CategoryStub;
import kryklyvets.project.restaurant.stubs.OrderStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void deleteOrder() throws Exception {
        mvc.perform(delete("/v1/orders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void createOrder() throws Exception {
        Order order = OrderStub.getRandomOrder();
        OrderRequest orderRequest = OrderStub.getOrderRequest();

        when(orderService.getById(OrderStub.ID)).thenReturn(OrderStub.getRandomOrder());
        when(orderService.create(orderRequest)).thenReturn(order);
        mvc.perform(postRequest("/v1/orders/create", orderRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(valueOf(order.getNumber()))));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void updateOrder() throws Exception {
        Order order = OrderStub.getRandomOrder();
        OrderRequest orderRequest = OrderStub.getOrderRequest();
        OrderRequest update = OrderStub.updateRandomOrder();

        when(orderService.update(1L, orderRequest)).thenReturn(order);

        when(orderService.getById(OrderStub.ID)).thenReturn(order);
        order.setNumber(update.getNumber());
        when(orderService.update(OrderStub.ID, update)).thenReturn(order);
        mvc.perform(putRequest("/v1/orders/1", update))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(valueOf(update.getNumber()))));

    }




    private MockHttpServletRequestBuilder postRequest(String url, OrderRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, OrderRequest request) {
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