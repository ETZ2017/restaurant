package kryklyvets.project.restaurant.services;
import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.exceptions.ClientNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IClientRepository;
import kryklyvets.project.restaurant.stubs.ClientStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class ClientServiceTest {
    private ClientService service;
    @Mock
    private IClientRepository clientRepository;

    @BeforeEach
    void setup(){
        service = new ClientService(clientRepository);
    }

    @Test
    void testSuccessfulGetById() {
        Client client = ClientStub.getRandomClient();

        Mockito.when(clientRepository.findById(Mockito.any())).thenReturn(Optional.of(client));
        Client result = service.getById(ClientStub.ID);

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
    void testNotSuccessfulGetById() {
        Mockito.when(clientRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(ClientNotFoundException.class, () -> service.getById(ClientStub.ID));

        assertEquals(e.getMessage(), "Could not find client " + ClientStub.ID);
    }

    @Test
    void testSuccessfulCreate() {
        var captor = ArgumentCaptor.forClass(Client.class);
        Client client = ClientStub.getRandomClient();

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(ClientStub.getRandomClient());

        Client result = service.create(ClientStub.getClientRequest());

        Mockito.verify(clientRepository, Mockito.atLeastOnce()).save(captor.capture());

//        assertEquals(client, captor.getValue());
        assertAll(
                () -> assertEquals(result.getFirstName(), client.getFirstName()),
                () -> assertEquals(result.getLastName(), client.getLastName()),
                () -> assertEquals(result.getStreet(), client.getStreet()),
                () -> assertEquals(result.getHouse(), client.getHouse())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(ClientStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(clientRepository, Mockito.atLeastOnce()).deleteById(captor.capture());

        assertEquals(ClientStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulUpdate() {
        ClientRequest client = ClientStub.updateRandomClient();

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(ClientStub.updateRandomClient());

        Client result = service.update(ClientStub.ID, ClientStub.updateRandomClient());

        assertAll(
                () -> assertEquals(result.getFirstName(), client.getFirstName()),
                () -> assertEquals(result.getLastName(), client.getLastName()),
                () -> assertEquals(result.getStreet(), client.getStreet()),
                () -> assertEquals(result.getHouse(), client.getHouse())
        );
    }

    @Test
    void testSuccessfulGetAll() {
        Client client = ClientStub.getRandomClient();

        Mockito.when(clientRepository.findAll()).thenReturn(List.of(client));
        List<Client> result = service.getAll();

        assertEquals(client.getFirstName(), result.get(0).getFirstName());
    }
}