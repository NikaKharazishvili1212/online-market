package model.exceptions;

/**
 * Unchecked exception for when a product is out of stock.
 */
public class OutOfStockException extends RuntimeException {
    
    public OutOfStockException(String message) {
        super(message);
    }
}