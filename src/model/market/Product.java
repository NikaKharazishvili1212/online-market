package model.market;

import java.math.BigDecimal;

/**
 * Represents a product available for purchase in the market.
 * Contains pricing and stock information.
 * Extends Entity class.
 */
public class Product extends Entity {

    public static BigDecimal taxRate;

    private String name;
    private BigDecimal price;
    private int stock;
    private Review[] reviews;

    static {
        taxRate = new BigDecimal("0.1"); // 10% tax added to product prices
    }

    public Product(String id, String name, BigDecimal price, int stock) {
        super(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reviews = new Review[0];
    }

    // Overrides
    @Override
    public String toString() {
        return "Product: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        return id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // Getters and setters
    public static BigDecimal getPriceWithTax(BigDecimal price) {
        return price.multiply(BigDecimal.ONE.add(taxRate));
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

    public Review[] getReviews() {
        return reviews;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }
}