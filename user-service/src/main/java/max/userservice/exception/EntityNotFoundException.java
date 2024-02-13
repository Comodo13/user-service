package max.userservice.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long entityId) {
        super("Entity not found with ID: " + entityId);
    }
}
