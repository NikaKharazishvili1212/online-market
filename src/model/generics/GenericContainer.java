package model.generics;

/**
 * Generic container class for holding any type of market item.
 * @param <T> the type of market item to contain
 */
public class GenericContainer<T> {
    
    private T item;
    private String containerId;

    public GenericContainer(String containerId, T item) {
        this.containerId = containerId;
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getItemDescription() {
        return "Container " + containerId + " contains: " + item.toString();
    }
}