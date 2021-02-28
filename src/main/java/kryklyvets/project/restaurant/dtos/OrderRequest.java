package kryklyvets.project.restaurant.dtos;

import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.entities.Dish;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class OrderRequest {
    private int number;

    private BigDecimal totalPrice;
}
