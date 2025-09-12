package model.market;

/**
 * Interface for entities that can receive reviews.
 */
public interface Reviewable {

    void addReview(Review review);
    
    double getAverageRating();
}