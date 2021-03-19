package kryklyvets.project.restaurant.stubs;

import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Client;

import java.time.LocalDateTime;
import java.util.HashSet;

public class ClientStub {
    public static final Long ID = 1L;

    public static Client getRandomClient(){
        return Client.builder().id(ID)
                .firstName("Test 1")
                .lastName("Test 1")
                .street("Test 1")
                .house("Test 1")
                .orders(new HashSet<>())
                .build();
    }

    public static ClientRequest getClientRequest(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setFirstName("Test 1");
        clientRequest.setLastName("Test 1");
        clientRequest.setStreet("Test 1");
        clientRequest.setHouse("Test 1");

        return clientRequest;
    }

    public static Client updateRandomClient(){
        return Client.builder().id(ID)
                .firstName("Test 2")
                .lastName("Test 2")
                .street("Test 2")
                .house("Test 2")
                .apt(12)
                .isDelete(false)
                .dateCreated(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .orders(new HashSet<>())
                .build();
    }
}
