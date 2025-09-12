package model.market;

import java.math.BigDecimal;

/**
 * Represents a product category for organizing products.
 * Extends MarketItem class.
 */
public class Category extends MarketItem {

    private String description;
    private Product[] products;

    public Category(String id, String name, String description) {
        super(id, name, BigDecimal.ZERO);
        this.description = description;
        this.products = new Product[0];
    }

    @Override
    public String toString() {
        return "Category: " + name;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Category))
            return false;
        return id.equals(((Category) o).id);
    }

    @Override
    public String getItemDescription() {
        return "Category: " + name + " - " + description + " (" + products.length + " products)";
    }

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}