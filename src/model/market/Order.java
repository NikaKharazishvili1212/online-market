package model.market;

/**
 * Represents a customer order with products and status.
 * Extends Entity class.
 */
public class Order extends Identifier {

    private String status;
    private Product[] products;
    private Payment payment;

    public Order(String id, String status, Product[] products, Payment payment) {
        super(id);
        this.status = status;
        this.products = products;
        this.payment = payment;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}