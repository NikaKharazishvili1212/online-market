package model.market;

import model.user.Customer;

/**
 * Represents a customer review/rating for a product.
 * Extends Entity class.
 */
public class Review extends Entity {

    private Integer rating;
    private String comment;
    private Customer customer;

    public Review(String id, Integer rating, String comment, Customer customer) {
        super(id);
        this.rating = rating;
        this.comment = comment;
        this.customer = customer;
    }

    // Overrides
    @Override
    public String toString() {
        return "Comment: " + comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Review))
            return false;
        return id.equals(((Review) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // Getters and setters
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
}