package model.market;

/**
 * Abstract base class providing unique identifier functionality.
 * Serves as the root of the entity hierarchy with common id management.
 */
public abstract class Identifier {

    protected String id;

    public Identifier(String id) {
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