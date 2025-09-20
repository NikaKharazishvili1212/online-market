package model.market;

import java.math.BigDecimal;
import java.util.*;

/**
 * Represents a product available for purchase in the market.
 * Contains pricing and stock information.
 * Extends MarketItem class and implements Pricable, Stockable, and Reviewable interfaces.
 */
public class Product extends MarketItem implements Pricable, Stockable, Reviewable {

    public static BigDecimal taxRate;
    private final String productCode;
    private int stock;
    private List<Review> reviews;

    static {
        taxRate = new BigDecimal("0.1");
    }

    public Product(String id, String name, BigDecimal price, int stock, String productCode) {
        super(id, name, price);
        this.stock = stock;
        this.productCode = productCode;
        this.reviews = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Product: " + name;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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
    public String getItemDescription() {
        return "Product: " + name + " (SKU: " + productCode + ") - $" + price + " - Stock: " + stock;
    }

    public static BigDecimal getPriceWithTax(BigDecimal price) {
        return price.multiply(BigDecimal.ONE.add(taxRate));
    }

    public String getProductCode() {
        return productCode;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public void restock(int quantity) {
        this.stock += quantity;
    }

    @Override
    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    // Pricable interface implementation
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Stockable interface implementation
    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }
}