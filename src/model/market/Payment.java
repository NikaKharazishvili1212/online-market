package model.market;

import java.math.BigDecimal;

/**
 * Represents a payment transaction for an order.
 * Extends Entity class.
 */
public class Payment extends Identifier {

    private BigDecimal amount;
    private String method;

    public Payment(String id, BigDecimal amount, String method) {
        super(id);
        this.amount = amount;
        this.method = method;
    }

    // Getters and setters
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