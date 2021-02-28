package kryklyvets.project.restaurant.dtos;

import kryklyvets.project.restaurant.entities.Category;
import lombok.Data;

import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class DishRequest {
    private String dish;

    private String ingredients;

    private BigDecimal price;

    private int amount;
}
