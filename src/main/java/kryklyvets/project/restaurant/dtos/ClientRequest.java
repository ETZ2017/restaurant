package kryklyvets.project.restaurant.dtos;

import kryklyvets.project.restaurant.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String firstName;

    private String lastName;

    private String street;

    private String house;

    private int apt;

    private Set<Order> orders;

    private Boolean isDelete;
}
