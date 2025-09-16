package model.exceptions;

/**
 * Unchecked exception for when discount exceeds maximum allowed.
 */
public class DiscountExceededException extends RuntimeException {
    
    public DiscountExceededException(String message) {
        super(message);
    }
}