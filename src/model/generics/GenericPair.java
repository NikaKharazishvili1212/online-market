package model.generics;

/**
 * Generic pair class for holding two related values.
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class GenericPair<K, V> {
    
    private K key;
    private V value;

    public GenericPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" + key + "=" + value + "}";
    }
}