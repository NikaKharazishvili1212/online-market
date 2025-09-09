package model.market;

/**
 * Abstract base class for all market entities with unique identifiers.
 * Provides common id field and access methods for all market objects.
 */
public abstract class Entity {

    protected String id;

    public Entity(String id) {
        this.id = id;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}