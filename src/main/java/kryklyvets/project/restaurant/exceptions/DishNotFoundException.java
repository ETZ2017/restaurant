package kryklyvets.project.restaurant.exceptions;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(Long id) {
        super("Could not find dish " + id);
    }
}
