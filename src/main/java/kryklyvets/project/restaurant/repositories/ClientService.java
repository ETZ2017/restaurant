package kryklyvets.project.restaurant.repositories;

import kryklyvets.project.restaurant.dtos.ClientRequest;
import kryklyvets.project.restaurant.entities.Client;
import kryklyvets.project.restaurant.exceptions.ClientNotFoundException;
import kryklyvets.project.restaurant.exceptions.DishNotFoundException;
import kryklyvets.project.restaurant.repositories.interfaces.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    public Client update(Long id, Client newClient){
        return repository.findById(id)
                .map(order -> {
                    order.setFirstName(newClient.getFirstName());
                    order.setLastName(newClient.getLastName());
                    order.setStreet(newClient.getStreet());
                    order.setHouse(newClient.getHouse());
                    order.setOrders(newClient.getOrders());
                    order.setApt(newClient.getApt());
                    order.setIsDelete(newClient.getIsDelete());
                    order.setDateModified(LocalDateTime.now());
                    return repository.save(newClient);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return repository.save(newClient);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
