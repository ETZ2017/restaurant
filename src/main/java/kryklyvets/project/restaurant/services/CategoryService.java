package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.exceptions.CategoryNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository repository;

    public List<Category> getAll(){
        return repository.findAll();
    }

    public Category getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category create(CategoryRequest request){
        var category = Category.builder().id(new Random().nextLong()).category(request.getCategory())
                .dishes(new HashSet<>()).isDelete(false)
                .dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now())
                .build();
        return  repository.save(category);
    }

    public Category update(Long id, CategoryRequest newCategory){
        return repository.findById(id)
                .map(category -> {
                    category.setCategory(newCategory.getCategory());
                    category.setDishes(newCategory.getDishes());
                    category.setIsDelete(newCategory.getIsDelete());
                    category.setDateModified(LocalDateTime.now());
                    return repository.save(category);
                })
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setId(id);
                    category.setCategory(newCategory.getCategory());
                    category.setDishes(newCategory.getDishes());
                    category.setIsDelete(newCategory.getIsDelete());
                    category.setDateModified(LocalDateTime.now());
                    category.setDateCreated(LocalDateTime.now());
                    return repository.save(category);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
