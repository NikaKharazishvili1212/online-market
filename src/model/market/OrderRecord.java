package model.market;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Record representing a simplified order with immutable data.
 */
public record OrderRecord(
    String orderId,
    String customerName,
    String productName,
    BigDecimal price,
    int quantity,
    LocalDateTime orderDate,
    OrderStatus status
) {
    
    public OrderRecord {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or blank");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }
    
    public BigDecimal getTotalAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    
    public boolean isDelivered() {
        return status == OrderStatus.DELIVERED;
    }
    
    public boolean canBeCancelled() {
        return status.canBeCancelled();
    }
    
    public static OrderRecord createQuickOrder(String orderId, String customerName, String productName, double price) {
        return new OrderRecord(
            orderId,
            customerName,
            productName,
            BigDecimal.valueOf(price),
            1,
            LocalDateTime.now(),
            OrderStatus.PENDING
        );
    }
}