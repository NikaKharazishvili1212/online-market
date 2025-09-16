package model.exceptions;

/**
 * Checked exception for general market-related errors.
 */
public class MarketException extends Exception {

    public MarketException(String message) {
        super(message);
    }
}