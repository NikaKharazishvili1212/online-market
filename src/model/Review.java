package model;

/**
 * Represents a customer review/rating for a product
 */
public class Review {
    
    private String id;
    private int rating;
    private String comment;

    public Review(String id, int rating, String comment) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}