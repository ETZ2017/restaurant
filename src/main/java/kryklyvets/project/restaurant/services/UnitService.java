package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.exceptions.UnitNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final IUnitRepository repository;

    public List<Unit> getAll(){
        return repository.findAll();
    }

    public Unit create(UnitRequest request){
        var unit = Unit.builder().id(new Random().nextLong()).unit(request.getUnit())
                .dishes(new HashSet<>()).isDelete(false)
                .dateCreated(LocalDateTime.now()).dateModified(LocalDateTime.now())
                .build();
        return  repository.save(unit);
    }

    public Unit getById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new UnitNotFoundException(id));
    }

    public Unit update(Long id, Unit newUnit){
        return repository.findById(id)
                .map(unit -> {
                    unit.setUnit(newUnit.getUnit());
                    unit.setDishes(newUnit.getDishes());
                    unit.setDishes(newUnit.getDishes());
                    unit.setIsDelete(newUnit.getIsDelete());
                    unit.setDateModified(LocalDateTime.now());
                    return repository.save(unit);
                })
                .orElseGet(() -> {
                    newUnit.setId(id);
                    return repository.save(newUnit);
                });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
