package kryklyvets.project.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.services.CategoryService;
import kryklyvets.project.restaurant.stubs.CategoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class V1CategoryControllerTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetAll() throws Exception {
        Category category = Category.builder().category("name").id(1L).build();
        ArrayList<Category> list = new ArrayList<Category>();
        Boolean add = list.add(category);
        when(categoryService.getAll()).thenReturn(list);

        mvc.perform(get("/v1/categories")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(category.getCategory())));
    }

    @Test
    void testGetById() throws Exception {
        var category = Category.builder().category("name").id(1L).build();
        when(categoryService.getById(1L)).thenReturn(category);

        mvc.perform(get("/v1/categories/1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(category.getCategory())));
    }

    @Test
    public void createCategory() throws Exception {
        Category category = Category.builder().category("name").id(1L).build();
        CategoryRequest categoryRequest = CategoryRequest.builder().category("name").build();

        when(categoryService.create(categoryRequest)).thenReturn(category);

        mvc.perform(post("/v1/categories/create")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(category.getCategory())));

    }

    @Test
    public void updateCategory() throws Exception {
        Category category = Category.builder().category("name 2").id(1L).build();

        when(categoryService.update(1L, category)).thenReturn(category);

        mvc.perform(put("/v1/categories/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(category.getCategory())));

    }

    @Test
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/categories/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }







    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private V1CategoryController controller;

    @BeforeEach
    void setup(){
        controller = new V1CategoryController(categoryService);
    }

    @Test
    void testSuccessfulGetById() {
        Category category = CategoryStub.getRandomCategory();

        when(categoryService.getById(Mockito.any())).thenReturn(category);
        Category result = controller.getById(CategoryStub.ID);

        assertAll(
                () -> assertEquals(result.getId(), category.getId()),
                () -> assertEquals(result.getCategory(), category.getCategory()),
                () -> assertEquals(result.getDishes().size(), category.getDishes().size()),
                () -> assertEquals(result.getIsDelete(), category.getIsDelete()),
                () -> assertEquals(result.getDateCreated(), category.getDateCreated()),
                () -> assertEquals(result.getDateModified(), category.getDateModified())
        );
    }

    @Test
    void testSuccessfulCreate() {
        Category category = CategoryStub.getRandomCategory();

        when(categoryService.create(Mockito.any())).thenReturn(CategoryStub.getRandomCategory());

        Category result = controller.create(CategoryStub.getCategoryRequest());

        assertAll(
                () -> assertEquals(result.getCategory(), category.getCategory())
        );
    }

    @Test
    void testSuccessfulDelete() {
        controller.delete(CategoryStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(categoryService, Mockito.atLeastOnce()).delete(captor.capture());

        assertEquals(CategoryStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulGetAll() {
        Category category = CategoryStub.getRandomCategory();

        when(categoryService.getAll()).thenReturn(List.of(category));
        List<Category> result = controller.getAll(1,1);

        assertEquals(category.getCategory(), result.get(0).getCategory());
    }

}
