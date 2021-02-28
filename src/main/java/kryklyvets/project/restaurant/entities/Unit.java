package kryklyvets.project.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "units")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    private Long id;

    private String unit;
    @OneToMany(mappedBy = "unit")
    private Set<Dish> dishes;

    private Boolean isDelete;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;
}
