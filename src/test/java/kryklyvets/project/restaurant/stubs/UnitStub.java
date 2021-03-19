package kryklyvets.project.restaurant.stubs;

import kryklyvets.project.restaurant.dtos.UnitRequest;
import kryklyvets.project.restaurant.entities.Unit;

import java.time.LocalDateTime;
import java.util.HashSet;

public class UnitStub {
    public static final Long ID = 1L;

    public static Unit getRandomUnit(){
        return Unit.builder().id(ID)
                .unit("Test 1 s")
                .dishes(new HashSet<>())
                .isDelete(false)
                .dateCreated(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }

    public static UnitRequest getUnitRequest(){
        UnitRequest unitRequest = new UnitRequest();
        unitRequest.setUnit("Test 1");

        return unitRequest;
    }

    public static Unit updateRandomUnit(){
        return Unit.builder().id(ID)
                .unit("Test 2 s")
                .dishes(new HashSet<>())
                .build();
    }
}
