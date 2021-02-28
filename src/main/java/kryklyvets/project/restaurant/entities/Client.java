package kryklyvets.project.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "clients")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String street;

    private String house;

    private int apt;
    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    private Boolean isDelete;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;
}
