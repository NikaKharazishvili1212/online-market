package model.market;

import java.math.BigDecimal;

/**
 * Interface for entities that have a price.
 */
public interface Pricable {

    BigDecimal getPrice();
    
    void setPrice(BigDecimal price);
}