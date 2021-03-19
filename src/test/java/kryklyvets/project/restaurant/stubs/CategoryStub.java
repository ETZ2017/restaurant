package kryklyvets.project.restaurant.stubs;

import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Category;

import java.time.LocalDateTime;
import java.util.HashSet;

public final class CategoryStub {
    public static final Long ID = 1L;

    public static Category getRandomCategory(){
        return Category.builder().id(ID)
                .category("Test 1")
                .dishes(new HashSet<>())
                .isDelete(false)
                .dateCreated(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }

    public static CategoryRequest getCategoryRequest(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategory("Test 1");

        return categoryRequest;
    }

    public static Category updateRandomCategory(){
        return Category.builder()
                .category("Test 2")
                .dishes(new HashSet<>())
                .isDelete(false)
                .dateCreated(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }
}
