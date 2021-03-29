package kryklyvets.project.restaurant.services;
import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Unit;
import kryklyvets.project.restaurant.exceptions.UnitNotFoundException;
import kryklyvets.project.restaurant.services.interfaces.IUnitRepository;
import kryklyvets.project.restaurant.stubs.UnitStub;
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
class UnitServiceTest {
    private UnitService service;
    @Mock
    private IUnitRepository unitRepository;
    public static final Long ID = 1L;

    @BeforeEach
    void setup(){
        service = new UnitService(unitRepository);
    }

    @Test
    void testSuccessfulGetById() {
        Unit unit = UnitStub.getRandomUnit();

        Mockito.when(unitRepository.findById(Mockito.any())).thenReturn(Optional.of(unit));
        Unit result = service.getById(UnitStub.ID);

        assertAll(
                () -> assertEquals(result.getId(), unit.getId()),
                () -> assertEquals(result.getUnit(), unit.getUnit()),
                () -> assertEquals(result.getDishes(), unit.getDishes()),
                () -> assertEquals(result.getIsDelete(), unit.getIsDelete()),
                () -> assertEquals(result.getDateCreated(), unit.getDateCreated()),
                () -> assertEquals(result.getDateModified(), unit.getDateModified())
        );
    }

    @Test
    void testNotSuccessfulGetById() {
        Mockito.when(unitRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(UnitNotFoundException.class, () -> service.getById(UnitStub.ID));

        assertEquals(e.getMessage(), "Could not find unit " + UnitStub.ID);
    }

    @Test
    void testSuccessfulCreate() {
        var captor = ArgumentCaptor.forClass(Unit.class);
        Unit unit = UnitStub.getRandomUnit();

        Mockito.when(unitRepository.save(Mockito.any())).thenReturn(UnitStub.getRandomUnit());

        Unit result = service.create(UnitStub.getUnitRequest());

        Mockito.verify(unitRepository, Mockito.atLeastOnce()).save(captor.capture());

//        assertEquals(client, captor.getValue());
        assertAll(
                () -> assertEquals(result.getUnit(), unit.getUnit())
        );
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(UnitStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(unitRepository, Mockito.atLeastOnce()).deleteById(captor.capture());

        assertEquals(UnitStub.ID, captor.getValue());
    }

    @Test
    void testSuccessfulUpdate() {
        UnitRequest unit = UnitStub.updateRandomUnit();

        Mockito.when(unitRepository.save(Mockito.any())).thenReturn(UnitStub.getUpdatedUnit());

        Unit result = service.update(UnitStub.ID, UnitStub.updateRandomUnit());

        assertEquals(unit.getUnit(), result.getUnit());
    }

    @Test
    void testSuccessfulGetAll() {
        Unit unit = UnitStub.getRandomUnit();

        Mockito.when(unitRepository.findAll()).thenReturn(List.of(unit));
        List<Unit> result = service.getAll();

        assertEquals(unit.getUnit(), result.get(0).getUnit());
    }
}