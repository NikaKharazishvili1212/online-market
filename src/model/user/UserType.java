package model.user;

/**
 * Complex enum representing different user types in the system.
 */
public enum UserType {
    
    STANDARD_CUSTOMER("STD", "Standard Customer", 100.0) {
        @Override
        public double getMaxDiscount() {
            return 10.0;
        }
        
        @Override
        public boolean canAccessPremiumFeatures() {
            return false;
        }
    },
    
    PREMIUM_CUSTOMER("PRE", "Premium Customer", 500.0) {
        @Override
        public double getMaxDiscount() {
            return 25.0;
        }
        
        @Override
        public boolean canAccessPremiumFeatures() {
            return true;
        }
    },
    
    VIP_CUSTOMER("VIP", "VIP Customer", 1000.0) {
        @Override
        public double getMaxDiscount() {
            return 40.0;
        }
        
        @Override
        public boolean canAccessPremiumFeatures() {
            return true;
        }
    },
    
    MODERATOR("MOD", "Content Moderator", 0.0) {
        @Override
        public double getMaxDiscount() {
            return 15.0;
        }
        
        @Override
        public boolean canAccessPremiumFeatures() {
            return true;
        }
    },
    
    SYSTEM_ADMIN("ADM", "System Administrator", 0.0) {
        @Override
        public double getMaxDiscount() {
            return 50.0;
        }
        
        @Override
        public boolean canAccessPremiumFeatures() {
            return true;
        }
    };
    
    private final String code;
    private final String description;
    private final double minimumBalance;
    
    UserType(String code, String description, double minimumBalance) {
        this.code = code;
        this.description = description;
        this.minimumBalance = minimumBalance;
    }
    
    public abstract double getMaxDiscount();
    public abstract boolean canAccessPremiumFeatures();
    
    public boolean meetsBalanceRequirement(double balance) {
        return balance >= minimumBalance;
    }
    
    public static UserType upgradeUserType(UserType current, double balance) {
        for (UserType type : values()) {
            if (balance >= type.minimumBalance && type.ordinal() > current.ordinal()) {
                return type;
            }
        }
        return current;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getMinimumBalance() {
        return minimumBalance;
    }
}