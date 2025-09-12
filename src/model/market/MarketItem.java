package model.market;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Abstract base class for all market items that can be purchased or sold.
 * Contains common properties like name, price, creation date, and availability.
 */
public abstract class MarketItem {

    protected String id;
    protected String name;
    protected BigDecimal price;
    protected Date createdDate;
    protected boolean isAvailable;

    public MarketItem(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdDate = new Date();
        this.isAvailable = true;
    }

    // Business method - common to all market items
    public abstract String getItemDescription();

    // Common functionality
    public void toggleAvailability() {
        this.isAvailable = !this.isAvailable;
    }

    public boolean isCurrentlyAvailable() {
        return isAvailable;
    }

    // Getters and setters
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}