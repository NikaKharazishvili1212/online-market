package model;

/**
 * Represents a customer order with products and status
 */
public class Order {
    
    private String id;
    private String status;
    private Product[] products;

    public Order(String id, String status, Product[] products) {
        this.id = id;
        this.status = status;
        this.products = products;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Product[] getProducts() { return products; }
    public void setProducts(Product[] products) { this.products = products; }
}