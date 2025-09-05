package model;

/**
 * Represents a customer review/rating for a product
 */
public class Review {

    private String id;
    private int rating;
    private String comment;
    private Customer customer;
    private Product product;

    public Review(String id, int rating, String comment, Customer customer, Product product) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.customer = customer;
        this.product = product;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}