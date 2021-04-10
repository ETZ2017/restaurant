package kryklyvets.project.restaurant.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kryklyvets.project.restaurant.dtos.CategoryRequest;
import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.dtos.OrderRequest;
import kryklyvets.project.restaurant.entities.Category;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.entities.Order;
import kryklyvets.project.restaurant.services.CategoryService;
import kryklyvets.project.restaurant.services.ClientService;
import kryklyvets.project.restaurant.stubs.CategoryStub;
import kryklyvets.project.restaurant.stubs.ClientStub;
import kryklyvets.project.restaurant.stubs.OrderStub;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
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
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void createClient() throws Exception {
        Client client = ClientStub.getRandomClient();
        ClientRequest clientRequest = ClientStub.getClientRequest();

        when(clientService.getById(ClientStub.ID)).thenReturn(ClientStub.getRandomClient());
        when(clientService.create(clientRequest)).thenReturn(client);
        mvc.perform(postRequest("/v1/clients/create", clientRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(client.getFirstName())));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void updateCategory() throws Exception {
        Client client = ClientStub.getRandomClient();
        ClientRequest clientRequest = ClientStub.getClientRequest();
        ClientRequest update = ClientStub.updateRandomClient();

        when(clientService.update(1L, clientRequest)).thenReturn(client);

        when(clientService.getById(CategoryStub.ID)).thenReturn(client);
        client.setFirstName(update.getFirstName());
        when(clientService.update(CategoryStub.ID, update)).thenReturn(client);
        mvc.perform(putRequest("/v1/clients/1", update))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(update.getFirstName())));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void deleteCategory() throws Exception {
        mvc.perform(delete("/v1/clients/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private MockHttpServletRequestBuilder postRequest(String url, ClientRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, ClientRequest request) {
        return put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }













    /*private V1ClientController controller;

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

    @Test
    public void createClient() throws Exception {
        Client client = ClientStub.getRandomClient();
        ClientRequest clientRequest = ClientStub.getClientRequest();

        when(clientService.getById(OrderStub.ID)).thenReturn(ClientStub.getRandomClient());
        when(clientService.create(clientRequest)).thenReturn(client);
        mvc.perform(postRequest("/v1/clients/create", clientRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(client.getFirstName())));

    }

    @Test
    public void updateClient() throws Exception {
        Client client = ClientStub.getRandomClient();
        ClientRequest clientRequest = ClientStub.getClientRequest();
        ClientRequest update = ClientStub.updateRandomClient();

        when(clientService.update(1L, clientRequest)).thenReturn(client);

        when(clientService.getById(ClientStub.ID)).thenReturn(client);
        client.setFirstName(update.getFirstName());
        when(clientService.update(ClientStub.ID, update)).thenReturn(client);
        mvc.perform(putRequest("/v1/clients/1", update))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(update.getFirstName())));

    }




    private MockHttpServletRequestBuilder postRequest(String url, ClientRequest request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, ClientRequest request) {
        return put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}
