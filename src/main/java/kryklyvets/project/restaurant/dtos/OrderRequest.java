package kryklyvets.project.restaurant.dtos;

import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.entities.Dish;
import kryklyvets.project.restaurant.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private int number;

    private BigDecimal totalPrice;

    private Client client;

    private Set<Dish> dishes;

    private Boolean isDelete;
}
