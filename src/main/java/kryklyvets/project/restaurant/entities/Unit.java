package kryklyvets.project.restaurant.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "units")
public class Unit {
    @Id
    private long id;

    private String unit;
    @OneToMany(mappedBy = "unit")
    private Set<Dish> dishes;

    private Boolean isDelete;

    private Date dateCreated;

    private Date dateModified;
}
