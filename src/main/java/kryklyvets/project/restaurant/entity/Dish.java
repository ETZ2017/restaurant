package kryklyvets.project.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "dishes")
public class Dish {
    @Id
    private long id;

    private String dish;
    @ManyToMany(mappedBy = "dishes")
    private Set<Category> category;

    private String ingredients;

    private BigDecimal price;

    private int amount;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
    @ManyToMany(mappedBy = "dishes")
    private Set<Order> orders;

    private Boolean isDelete;

    private Date dateCreated;

    private Date dateModified;
}
