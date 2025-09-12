package model.market;

/**
 * Final utility class for discount calculations.
 */
public final class DiscountCalculator {
    
    public static final double MAX_DISCOUNT = 0.5;
    
    public static final double calculateDiscount(double price) {
        return price * 0.1;
    }
    
    private DiscountCalculator() {}
}