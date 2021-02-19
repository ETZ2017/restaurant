package kryklyvets.project.restaurant.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "categories")
public class Category {
    @Id
    private long id;

    private String category;
    @ManyToMany
    @JoinTable(name = "category_dish",
            joinColumns = @JoinColumn(name="category_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="dish_id", referencedColumnName="id"))
    private Set<Dish> dishes;

    private Boolean isDelete;

    private Date dateCreated;

    private Date dateModified;
}
