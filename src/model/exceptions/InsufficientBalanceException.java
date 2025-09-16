package model.exceptions;

/**
 * Unchecked exception for when a customer doesn't have enough balance.
 */
public class InsufficientBalanceException extends RuntimeException {
    
    public InsufficientBalanceException(String message) {
        super(message);
    }
}