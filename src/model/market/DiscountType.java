package model.market;

import java.time.LocalDate;

/**
 * Complex enum for different discount types with expiration logic.
 */
public enum DiscountType {
    
    SEASONAL("Seasonal Sale", 0.15) {
        @Override
        public boolean isValidForDate(LocalDate date) {
            int month = date.getMonthValue();
            return (month == 12) || (month >= 6 && month <= 8);
        }
        
        @Override
        public String getDiscountTerms() {
            return "Valid during holiday and summer seasons";
        }
    },
    
    BULK("Bulk Purchase", 0.20) {
        @Override
        public boolean isValidForDate(LocalDate date) {
            return true;
        }
        
        @Override
        public String getDiscountTerms() {
            return "Applies to orders over 5 items";
        }
    },
    
    FIRST_TIME("First Time Buyer", 0.10) {
        @Override
        public boolean isValidForDate(LocalDate date) {
            return true;
        }
        
        @Override
        public String getDiscountTerms() {
            return "Available for first purchase only";
        }
    },
    
    LOYALTY("Loyalty Reward", 0.25) {
        @Override
        public boolean isValidForDate(LocalDate date) {
            return date.getDayOfMonth() == 1;
        }
        
        @Override
        public String getDiscountTerms() {
            return "Available on first day of each month for loyal customers";
        }
    },
    
    CLEARANCE("Clearance Sale", 0.50) {
        @Override
        public boolean isValidForDate(LocalDate date) {
            return date.getMonthValue() == 1;
        }
        
        @Override
        public String getDiscountTerms() {
            return "January clearance event only";
        }
    };
    
    private final String description;
    private final double discountRate;
    private LocalDate startDate;
    private LocalDate endDate;
    
    DiscountType(String description, double discountRate) {
        this.description = description;
        this.discountRate = discountRate;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusMonths(1);
    }
    
    public abstract boolean isValidForDate(LocalDate date);
    public abstract String getDiscountTerms();
    
    public double applyDiscount(double price) {
        return price * (1 - discountRate);
    }
    
    public boolean isCurrentlyValid() {
        LocalDate today = LocalDate.now();
        return isValidForDate(today) && 
               !today.isBefore(startDate) && 
               !today.isAfter(endDate);
    }
    
    public void setValidityPeriod(LocalDate start, LocalDate end) {
        this.startDate = start;
        this.endDate = end;
    }
    
    public static DiscountType getBestDiscount(double price, int quantity) {
        if (quantity > 5) return BULK;
        if (price > 100) return LOYALTY;
        return SEASONAL;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
}