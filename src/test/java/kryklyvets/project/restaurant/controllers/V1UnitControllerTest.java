package kryklyvets.project.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kryklyvets.project.restaurant.dtos.DishRequest;
import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.services.OrderService;
import kryklyvets.project.restaurant.services.UnitService;
import kryklyvets.project.restaurant.stubs.DishStub;
import kryklyvets.project.restaurant.stubs.OrderStub;
import kryklyvets.project.restaurant.stubs.UnitStub;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class V1UnitControllerTest {
    @MockBean
    private UnitService unitService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetAll() throws Exception {
        Unit unit = Unit.builder().unit("test").id(1L).build();
        ArrayList<Unit> list = new ArrayList<Unit>();
        Boolean add = list.add(unit);
        when(unitService.getAll()).thenReturn(list);

        mvc.perform(get("/v1/units")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(unit.getUnit())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        Unit unit = Unit.builder().unit("test").id(1L).build();
        when(unitService.getById(1L)).thenReturn(unit);

        mvc.perform(get("/v1/units/1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(unit.getUnit())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/units/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void createUnit() throws Exception {
        Unit unit = UnitStub.getRandomUnit();
        UnitRequest unitRequest = UnitStub.getUnitRequest();

        when(unitService.getById(UnitStub.ID)).thenReturn(UnitStub.getRandomUnit());
        when(unitService.create(unitRequest)).thenReturn(unit);
        mvc.perform(postRequest("/v1/units/create", unitRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(unit.getUnit())));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void updateUnit() throws Exception {
        Unit unit = UnitStub.getRandomUnit();
        UnitRequest unitRequest = UnitStub.getUnitRequest();
        UnitRequest update = UnitStub.updateRandomUnit();

        when(unitService.update(1L, unitRequest)).thenReturn(unit);

        when(unitService.getById(UnitStub.ID)).thenReturn(unit);
        unit.setUnit(update.getUnit());
        when(unitService.update(UnitStub.ID, update)).thenReturn(unit);
        mvc.perform(putRequest("/v1/units/1", update))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(update.getUnit())));

    }




    private MockHttpServletRequestBuilder postRequest(String url, UnitRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, UnitRequest request) {
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