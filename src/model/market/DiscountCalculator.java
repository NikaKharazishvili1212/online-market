package model.market;

/**
 * Final utility class for discount calculations.
 */
public final class DiscountCalculator {
    
    public final double MAX_DISCOUNT = 0.5;
    
    public double calculateFinalDiscount(double price) {
        return price * MAX_DISCOUNT;
    }
    
    private DiscountCalculator() {}
}