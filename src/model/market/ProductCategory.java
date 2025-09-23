package model.market;

/**
 * Complex enum for product categories with tax rates and validation.
 */
public enum ProductCategory {
    
    ELECTRONICS("ELEC", "Electronics", 0.18, true) {
        @Override
        public boolean requiresSpecialHandling() {
            return true;
        }
        
        @Override
        public String getShippingRestrictions() {
            return "Fragile items require special packaging";
        }
    },
    
    BOOKS("BOOK", "Books and Media", 0.05, false) {
        @Override
        public boolean requiresSpecialHandling() {
            return false;
        }
        
        @Override
        public String getShippingRestrictions() {
            return "No special restrictions";
        }
    },
    
    CLOTHING("CLTH", "Clothing and Apparel", 0.12, false) {
        @Override
        public boolean requiresSpecialHandling() {
            return false;
        }
        
        @Override
        public String getShippingRestrictions() {
            return "May require try-on returns";
        }
    },
    
    FOOD("FOOD", "Food and Beverages", 0.08, true) {
        @Override
        public boolean requiresSpecialHandling() {
            return true;
        }
        
        @Override
        public String getShippingRestrictions() {
            return "Perishable items - expedited shipping required";
        }
    },
    
    FURNITURE("FURN", "Furniture and Home", 0.15, true) {
        @Override
        public boolean requiresSpecialHandling() {
            return true;
        }
        
        @Override
        public String getShippingRestrictions() {
            return "Large items require special delivery";
        }
    };
    
    static {
        System.out.println("ProductCategory enum initialized with " + values().length + " categories");
    }
    
    private final String code;
    private final String description;
    private final double taxRate;
    private final boolean requiresAgeVerification;
    
    ProductCategory(String code, String description, double taxRate, boolean requiresAgeVerification) {
        this.code = code;
        this.description = description;
        this.taxRate = taxRate;
        this.requiresAgeVerification = requiresAgeVerification;
    }
    
    public abstract boolean requiresSpecialHandling();
    public abstract String getShippingRestrictions();
    
    public double calculateTax(double price) {
        return price * taxRate;
    }
    
    public double getPriceWithTax(double price) {
        return price + calculateTax(price);
    }
    
    public static ProductCategory getCategoryByCode(String code) {
        for (ProductCategory category : values()) {
            if (category.code.equals(code)) {
                return category;
            }
        }
        return BOOKS;
    }
    
    public boolean isRestricted() {
        return requiresAgeVerification;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getTaxRate() {
        return taxRate;
    }
    
    public boolean requiresAgeVerification() {
        return requiresAgeVerification;
    }
}