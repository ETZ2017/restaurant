package kryklyvets.project.restaurant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "clients")
public class Client {
    @Id
    private long id;

    private String firstName;

    private String lastName;

    private String street;

    private String house;

    private int apt;
    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    private Boolean isDelete;

    private Date dateCreated;

    private Date dateModified;
}
