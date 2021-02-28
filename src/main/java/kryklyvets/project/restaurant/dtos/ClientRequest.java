package kryklyvets.project.restaurant.dtos;

import lombok.Data;

@Data
public class ClientRequest {
    private String firstName;

    private String lastName;

    private String street;

    private String house;

    private int apt;
}
