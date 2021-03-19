package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.services.ClientService;
import kryklyvets.project.restaurant.stubs.ClientStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class ClientControllerTest {
    private V1ClientController controller;
    @Mock
    private ClientService clientService;

    @BeforeEach
    void setup(){
        controller = new V1ClientController(clientService);
    }

    @Test
    void testSuccessfulGetById() {
        Client client = ClientStub.getRandomClient();

        Mockito.when(clientService.getById(Mockito.any())).thenReturn(client);
        Client result = controller.getById(ClientStub.ID);

        assertAll(
                () -> assertEquals(result.getId(), client.getId()),
                () -> assertEquals(result.getFirstName(), client.getFirstName()),
                () -> assertEquals(result.getLastName(), client.getLastName()),
                () -> assertEquals(result.getOrders().size(), client.getOrders().size()),
                () -> assertEquals(result.getApt(), client.getApt()),
                () -> assertEquals(result.getIsDelete(), client.getIsDelete()),
                () -> assertEquals(result.getDateCreated(), client.getDateCreated()),
                () -> assertEquals(result.getDateModified(), client.getDateModified())
        );
    }

    @Test
    void testSuccessfulCreate() {
        Client client = ClientStub.getRandomClient();

        Mockito.when(clientService.create(Mockito.any())).thenReturn(ClientStub.getRandomClient());

        Client result = controller.create(ClientStub.getClientRequest());

        assertAll(
                () -> assertEquals(result.getFirstName(), client.getFirstName()),
                () -> assertEquals(result.getLastName(), client.getLastName()),
                () -> assertEquals(result.getStreet(), client.getStreet()),
                () -> assertEquals(result.getHouse(), client.getHouse())
        );
    }

    @Test
    void testSuccessfulDelete() {
        controller.delete(ClientStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(clientService, Mockito.atLeastOnce()).delete(captor.capture());

        assertEquals(ClientStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulGetAll() {
        Client client = ClientStub.getRandomClient();

        Mockito.when(clientService.getAll()).thenReturn(List.of(client));
        List<Client> result = controller.getAll(1,1);

        assertEquals(client.getFirstName(), result.get(0).getFirstName());
    }
}
