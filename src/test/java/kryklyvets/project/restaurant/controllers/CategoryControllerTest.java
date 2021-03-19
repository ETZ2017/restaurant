package kryklyvets.project.restaurant.controllers;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class CategoryControllerTest {
    private V1CategoryController controller;
    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setup(){
        controller = new V1CategoryController(categoryService);
    }

    @Test
    void testSuccessfulGetById() {
        Category category = CategoryStub.getRandomCategory();

        Mockito.when(categoryService.getById(Mockito.any())).thenReturn(category);
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

        Mockito.when(categoryService.create(Mockito.any())).thenReturn(CategoryStub.getRandomCategory());

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

        Mockito.when(categoryService.getAll()).thenReturn(List.of(category));
        List<Category> result = controller.getAll(1,1);

        assertEquals(category.getCategory(), result.get(0).getCategory());
    }

}
