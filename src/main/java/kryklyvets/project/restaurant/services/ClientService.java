package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.exceptions.ClientNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final IClientRepository repository;

    public List<Client> getAll(){
        return repository.findAll();
    }

    public Client getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Client create(ClientRequest request){
        var client = Client.builder().id(new Random().nextLong()).firstName(request.getFirstName())
                .lastName(request.getLastName()).street(request.getStreet())
                .house(request.getHouse()).apt(request.getApt())
                .orders(new HashSet<>()).isDelete(false)
                .dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now())
                .build();
        return  repository.save(client);
    }

    public Client update(Long id, ClientRequest newClient){
        return repository.findById(id)
                .map(client -> {
                    client.setFirstName(newClient.getFirstName());
                    client.setLastName(newClient.getLastName());
                    client.setStreet(newClient.getStreet());
                    client.setHouse(newClient.getHouse());
                    client.setOrders(newClient.getOrders());
                    client.setApt(newClient.getApt());
                    client.setIsDelete(newClient.getIsDelete());
                    client.setDateModified(LocalDateTime.now());
                    return repository.save(client);
                })
                .orElseGet(() -> {
                    Client client = new Client();
                    client.setId(id);
                    client.setFirstName(newClient.getFirstName());
                    client.setLastName(newClient.getLastName());
                    client.setStreet(newClient.getStreet());
                    client.setHouse(newClient.getHouse());
                    client.setOrders(newClient.getOrders());
                    client.setApt(newClient.getApt());
                    client.setIsDelete(newClient.getIsDelete());
                    client.setDateModified(LocalDateTime.now());
                    client.setDateCreated(LocalDateTime.now());
                    return repository.save(client);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
