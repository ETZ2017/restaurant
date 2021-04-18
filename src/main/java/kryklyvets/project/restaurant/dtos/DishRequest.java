package kryklyvets.project.restaurant.dtos;

import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.entities.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {
    private String dish;

    private String ingredients;

    private BigDecimal price;

    private Currency currency;

    private Set<Category> category;

    private Unit unit;

    private Set<Order> orders;

    private int amount;

    private Boolean isDelete;

}
