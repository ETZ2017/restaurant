package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.services.CategoryService;
import kryklyvets.project.restaurant.services.ClientService;
import kryklyvets.project.restaurant.stubs.ClientStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class V1ClientControllerTest {
    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetAll() throws Exception {
        Client client = Client.builder().firstName("name").lastName("last name").id(1L).build();
        ArrayList<Client> list = new ArrayList<Client>();
        Boolean add = list.add(client);
        when(clientService.getAll()).thenReturn(list);

        mvc.perform(get("/v1/clients")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(client.getFirstName())));
    }

    @Test
    void testGetById() throws Exception {
        Client client = Client.builder().firstName("name").lastName("last name").id(1L).build();
        when(clientService.getById(1L)).thenReturn(client);

        mvc.perform(get("/v1/clients/1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(client.getFirstName())));
    }

    @Test
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/clients/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }













    private V1ClientController controller;

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
