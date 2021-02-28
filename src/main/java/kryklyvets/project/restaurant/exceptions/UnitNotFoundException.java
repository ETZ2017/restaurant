package kryklyvets.project.restaurant.exceptions;

public class UnitNotFoundException extends RuntimeException {

    public UnitNotFoundException(Long id) {
        super("Could not find unit " + id);
    }
}
