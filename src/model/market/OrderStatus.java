package model.market;

/**
 * Complex enum representing order status with additional functionality.
 */
public enum OrderStatus {
    
    PENDING("PND", "Order received but not processed", 1) {
        @Override
        public boolean canBeCancelled() {
            return true;
        }
        
        @Override
        public String getStatusMessage() {
            return "Your order is waiting to be processed";
        }
    },
    
    PROCESSING("PRC", "Order is being prepared", 2) {
        @Override
        public boolean canBeCancelled() {
            return true;
        }
        
        @Override
        public String getStatusMessage() {
            return "We're preparing your order for shipment";
        }
    },
    
    SHIPPED("SHP", "Order has been shipped", 3) {
        @Override
        public boolean canBeCancelled() {
            return false;
        }
        
        @Override
        public String getStatusMessage() {
            return "Your order is on the way! Tracking number: " + getTrackingInfo();
        }
    },
    
    DELIVERED("DLV", "Order has been delivered", 4) {
        @Override
        public boolean canBeCancelled() {
            return false;
        }
        
        @Override
        public String getStatusMessage() {
            return "Your order has been delivered successfully";
        }
    },
    
    CANCELLED("CAN", "Order was cancelled", 0) {
        @Override
        public boolean canBeCancelled() {
            return false;
        }
        
        @Override
        public String getStatusMessage() {
            return "This order has been cancelled";
        }
    };
    
    static {
        System.out.println("OrderStatus enum loaded with " + values().length + " status types");
    }
    
    private final String code;
    private final String description;
    private final int priority;
    private String trackingInfo;
    
    OrderStatus(String code, String description, int priority) {
        this.code = code;
        this.description = description;
        this.priority = priority;
        this.trackingInfo = "Not available";
    }
    
    public abstract boolean canBeCancelled();
    public abstract String getStatusMessage();
    
    public void setTrackingInfo(String trackingInfo) {
        this.trackingInfo = trackingInfo;
    }
    
    public String getTrackingInfo() {
        return trackingInfo;
    }
    
    public static OrderStatus getByCode(String code) {
        for (OrderStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return PENDING;
    }
    
    public boolean isHigherPriorityThan(OrderStatus other) {
        return this.priority > other.priority;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getPriority() {
        return priority;
    }
}