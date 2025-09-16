package service;

/**
 * AutoCloseable class for managing market resources.
 */
public class MarketResource implements AutoCloseable {
    
    private String resourceName;
    
    public MarketResource(String resourceName) {
        this.resourceName = resourceName;
        System.out.println("Resource opened: " + resourceName);
    }
    
    public void useResource() {
        System.out.println("Using resource: " + resourceName);
    }
    
    @Override
    public void close() {
        System.out.println("Resource closed: " + resourceName);
    }
}