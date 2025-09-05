package model;

import java.math.BigDecimal;

/**
 * Represents a payment transaction for an order
 */
public class Payment {

    private String id;
    private BigDecimal amount;
    private String method;

    public Payment(String id, BigDecimal amount, String method) {
        this.id = id;
        this.amount = amount;
        this.method = method;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}