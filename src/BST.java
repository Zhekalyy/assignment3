import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
/**
 * A generic implementation of a binary search tree (BST) that stores key-value pairs.
 * The keys must implement the Comparable interface for comparison purposes.
 *
 * @param <K> The type of keys in the binary search tree.
 * @param <V> The type of values associated with the keys.
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size;
    /**
     * Inner class representing a node in the binary search tree.
     * Each node stores a key-value pair and references to its left and right children.
     */
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        /**
         * Constructs a new Node with the specified key and value.
         *
         * @param key The key of the node.
         * @param val The value associated with the key.
         */
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    /**
     * Inserts a key-value pair into the binary search tree.
     * If the key already exists, updates the corresponding value.
     *
     * @param key The key to insert.
     * @param val The value associated with the key.
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }
    // Recursive helper method to insert a key-value pair into the subtree rooted at node
    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        return node;
    }
    /**
     * Retrieves the value associated with the given key from the binary search tree.
     *
     * @param key The key for which to retrieve the value.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.val;
            }
        }
        return null;
    }
    /**
     * Deletes the key-value pair associated with the given key from the binary search tree.
     *
     * @param key The key of the pair to delete.
     */
    public void delete(K key) {
        root = delete(root, key);
    }
    // Recursive helper method to delete a key-value pair from the subtree rooted at node
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
            size--;
        }
        return node;
    }
    // Recursive helper method to find the minimum key node in a subtree rooted at node
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }
    // Recursive helper method to delete the minimum key node from a subtree rooted at node
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }
    /**
     * Returns the number of key-value pairs in the binary search tree.
     *
     * @return The number of elements in the binary search tree.
     */
    public int size() {
        return size;
    }
    /**
     * Returns an iterator over the key-value pairs in the binary search tree.
     * The iterator traverses the tree in in-order (sorted) fashion.
     *
     * @return An iterator over the key-value pairs.
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator(root);
    }
    /**
     * Inner class representing an iterator that traverses the binary search tree in in-order fashion.
     */
    private class InOrderIterator implements Iterator<Entry<K, V>> {
        private final Deque<Node> stack = new ArrayDeque<>();
        // Constructor initializes the iterator with the root node and pushes left nodes onto the stack
        public InOrderIterator(Node root) {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        // Implementation of hasNext() method
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        // Implementation of next() method
        @Override
        public Entry<K, V> next() {
            Node current = stack.pop();
            pushLeft(current.right);
            return new Entry<>(current.key, current.val);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    /**
     * Inner class representing a key-value pair in the binary search tree.
     *
     * @param <K> The type of keys in the entry.
     * @param <V> The type of values in the entry.
     */
    public static class Entry<K, V> {
        private final K key;
        private final V value;
        /**
         * Constructs a new entry with the specified key and value.
         *
         * @param key   The key of the entry.
         * @param value The value associated with the key.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /**
         * Returns the key of the entry.
         *
         * @return The key of the entry.
         */
        public K getKey() {
            return key;
        }
        /**
         * Returns the value associated with the key.
         *
         * @return The value associated with the key.
         */
        public V getValue() {
            return value;
        }
    }
}