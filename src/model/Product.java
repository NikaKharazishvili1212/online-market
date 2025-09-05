package model;

import java.math.BigDecimal;

/**
 * Represents a product available for purchase in the market
 * Contains pricing and stock information
 */
public class Product {

    public static BigDecimal taxRate;

    private String id;
    private String name;
    private BigDecimal price;
    private int stock;
    private Category category;

    static {
        taxRate = new BigDecimal("0.1");  // 10% tax added to product prices
    }

    public Product(String id, String name, BigDecimal price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Getters and setters
    public static BigDecimal getPriceWithTax(BigDecimal price) {
        return price.multiply(BigDecimal.ONE.add(taxRate));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}