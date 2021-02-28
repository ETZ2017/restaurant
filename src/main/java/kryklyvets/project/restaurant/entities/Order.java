package kryklyvets.project.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private Long id;

    private int number;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToMany
    @JoinTable(name = "order_dish",
                joinColumns = @JoinColumn(name="order_id", referencedColumnName="id"),
                inverseJoinColumns = @JoinColumn(name="dish_id", referencedColumnName="id"))
    private Set<Dish> dishes;

    private BigDecimal totalPrice;

    private Boolean isDelete;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;
}
