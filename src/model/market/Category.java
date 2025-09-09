package model.market;

/**
 * Represents a product category for organizing products.
 * Extends Entity class.
 */
public class Category extends Entity {

    private String name;
    private String description;
    private Product[] products;

    public Category(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        this.products = new Product[0];
    }

    // Overrides
    @Override
    public String toString() {
        return "Category: " + name;
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
    public int hashCode() {
        return id.hashCode();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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