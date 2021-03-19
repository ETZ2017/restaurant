package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.exceptions.CategoryNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.ICategoryRepository;
import kryklyvets.project.restaurant.stubs.CategoryStub;
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

@ExtendWith({MockitoExtension.class})
class CategoryServiceTest {
    private CategoryService service;
    @Mock
    private ICategoryRepository categoryRepository;

    @BeforeEach
    void setup(){
        service = new CategoryService(categoryRepository);
    }

    @Test
    void testSuccessfulGetById() {
        Category category = CategoryStub.getRandomCategory();

        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(category));
        Category result = service.getById(CategoryStub.ID);

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
    void testNotSuccessfulGetById() {
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(CategoryNotFoundException.class, () -> service.getById(CategoryStub.ID));

        assertEquals(e.getMessage(), "Could not find category " + CategoryStub.ID);
    }

    @Test
    void testSuccessfulCreate() {
        var captor = ArgumentCaptor.forClass(Category.class);
        Category category = CategoryStub.getRandomCategory();

        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(CategoryStub.getRandomCategory());

        Category result = service.create(CategoryStub.getCategoryRequest());

        Mockito.verify(categoryRepository, Mockito.atLeastOnce()).save(captor.capture());

//        assertEquals(client, captor.getValue());
        assertAll(
                () -> assertEquals(result.getCategory(), category.getCategory())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(CategoryStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(categoryRepository, Mockito.atLeastOnce()).deleteById(captor.capture());

        assertEquals(CategoryStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulUpdate() {
        Category category = CategoryStub.updateRandomCategory();

        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(CategoryStub.updateRandomCategory());

        Category result = service.update(CategoryStub.ID, CategoryStub.updateRandomCategory());

        assertEquals(result.getCategory(), category.getCategory());
    }

    @Test
    void testSuccessfulGetAll() {
        Category category = CategoryStub.getRandomCategory();

        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category));
        List<Category> result = service.getAll();

        assertEquals(category.getCategory(), result.get(0).getCategory());
    }
}