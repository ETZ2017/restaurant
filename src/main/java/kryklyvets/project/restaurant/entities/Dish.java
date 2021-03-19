package kryklyvets.project.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "dishes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    private Long id;

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

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;
}
