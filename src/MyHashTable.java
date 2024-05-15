import java.util.Random;

/**
 * A generic hash table implementation that stores key-value pairs.
 * Collision resolution is handled using chaining.
 *
 * @param <K> The type of keys in the hash table.
 * @param <V> The type of values in the hash table.
 */
public class MyHashTable<K, V> {

    /**
     * Inner class representing a node in the hash table's chains.
     * Each node stores a key-value pair and a reference to the next node in the chain.
     *
     * @param <K> The type of keys in the node.
     * @param <V> The type of values in the node.
     */
    protected class HashNode<K, V> {
        protected K key;
        protected V value;
        protected HashNode<K, V> next;

        /**
         * Constructs a new HashNode with the specified key and value.
         *
         * @param key   The key of the node.
         * @param value The value of the node.
         */
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns a string representation of the node.
         *
         * @return A string representation of the node.
         */
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    protected HashNode<K, V>[] chainArray; // Array of chains (buckets)
    private int M = 11; // Default number of chains
    private int size; // Number of elements in the hash table

    /**
     * Constructs a new hash table with default parameters.
     * The default number of chains is set to 11.
     */
    public MyHashTable() {
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    /**
     * Constructs a new hash table with the specified number of chains.
     *
     * @param M The number of chains in the hash table.
     */
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Computes the hash value for the given key.
     *
     * @param key The key for which to compute the hash value.
     * @return The hash value for the key.
     */
    protected int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * Adds a key-value pair to the hash table.
     *
     * @param key   The key to add.
     * @param value The value associated with the key.
     */
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> current = chainArray[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key for which to retrieve the value.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the key-value pair associated with the given key from the hash table.
     *
     * @param key The key of the pair to remove.
     * @return The value associated with the removed key, or null if the key is not found.
     */
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Checks if the hash table contains the specified value.
     *
     * @param value The value to search for.
     * @return True if the value is found in the hash table, false otherwise.
     */
    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> current = node;
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    /**
     * Retrieves the key associated with the specified value.
     *
     * @param value The value for which to retrieve the key.
     * @return The key associated with the value, or null if the value is not found.
     */
    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            HashNode<K, V> current = node;
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }
}