package kryklyvets.project.restaurant.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    private long id;

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

    private Date dateCreated;

    private Date dateModified;
}
