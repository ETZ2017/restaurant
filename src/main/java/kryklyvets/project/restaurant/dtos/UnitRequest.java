package kryklyvets.project.restaurant.dtos;

import kryklyvets.project.restaurant.entities.Dish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitRequest {
    private String unit;

    private Set<Dish> dishes;

    private Boolean isDelete;
}
