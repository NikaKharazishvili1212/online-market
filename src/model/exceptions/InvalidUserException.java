package model.exceptions;

/**
 * Unchecked exception for invalid user operations.
 */
public class InvalidUserException extends RuntimeException {
    
    public InvalidUserException(String message) {
        super(message);
    }
}