package model.market;

/**
 * Interface for entities that manage stock/inventory.
 */
public interface Stockable {

    int getStock();

    void setStock(int stock);
    
    void restock(int quantity);
}