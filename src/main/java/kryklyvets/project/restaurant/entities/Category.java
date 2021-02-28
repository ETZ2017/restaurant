package kryklyvets.project.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private Long id;

    private String category;
    @ManyToMany
    @JoinTable(name = "category_dish",
            joinColumns = @JoinColumn(name="category_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="dish_id", referencedColumnName="id"))
    private Set<Dish> dishes;

    private Boolean isDelete;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;
}
