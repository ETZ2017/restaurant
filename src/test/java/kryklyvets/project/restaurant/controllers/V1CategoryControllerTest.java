package kryklyvets.project.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.services.CategoryService;
import kryklyvets.project.restaurant.stubs.CategoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
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
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetAll() throws Exception {
        Category category = CategoryStub.getRandomCategory();
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        Category category = CategoryStub.getRandomCategory();
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void createCategory() throws Exception {
        Category category = CategoryStub.getRandomCategory();
        CategoryRequest categoryRequest = CategoryStub.getCategoryRequest();

        when(categoryService.getById(CategoryStub.ID)).thenReturn(CategoryStub.getRandomCategory());
        when(categoryService.create(categoryRequest)).thenReturn(category);
        mvc.perform(postRequest("/v1/categories/create", categoryRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(category.getCategory())));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void updateCategory() throws Exception {
        Category category = CategoryStub.getRandomCategory();
        CategoryRequest categoryRequest = CategoryStub.getCategoryRequest();
        CategoryRequest update = CategoryStub.updateRandomCategory();

        when(categoryService.update(1L, categoryRequest)).thenReturn(category);

        when(categoryService.getById(CategoryStub.ID)).thenReturn(category);
        category.setCategory(update.getCategory());
        when(categoryService.update(CategoryStub.ID, update)).thenReturn(category);
        mvc.perform(putRequest("/v1/categories/1", update))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(update.getCategory())));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/categories/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private MockHttpServletRequestBuilder postRequest(String url, CategoryRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, CategoryRequest request) {
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






    /*private V1CategoryController controller;

    @BeforeEach
    void setup(){
        controller = new V1CategoryController(categoryService);
    }

    @Test
    void testSuccessfulGetById() {
        Category category = CategoryStub.getRandomCategory();

        when(categoryService.getById(any())).thenReturn(category);
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

        when(categoryService.create(any())).thenReturn(CategoryStub.getRandomCategory());

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
    }*/

}
