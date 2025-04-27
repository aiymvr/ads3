import java.util.*;

/**
 * Binary Search Tree implementation with size tracking and in-order traversal iterator.
 * Key-value pairs are accessible during iteration.
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValue<K, V>> {

    // Private inner class for tree nodes
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root; // Root of the BST
    private int size;  // Number of nodes in the BST

    // Public inner class to store key-value pairs for iteration
    public static class KeyValue<K, V> {
        private final K key;
        private final V value;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Inserts a key-value pair into the BST.
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }

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
            node.val = val; // If key already exists, update value
        }
        return node;
    }

    /**
     * Retrieves the value associated with the given key.
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
     * Deletes a node with the given key from the BST.
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    // Helper method to find the minimum node in a subtree
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    // Helper method to delete the minimum node in a subtree
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * Returns the number of nodes (key-value pairs) in the BST.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an in-order iterator over key-value pairs.
     */
    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        List<KeyValue<K, V>> list = new ArrayList<>();
        inOrder(root, list);
        return list.iterator();
    }

    // Helper method for in-order traversal
    private void inOrder(Node node, List<KeyValue<K, V>> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(new KeyValue<>(node.key, node.val)); // Add key-value pair during in-order traversal
        inOrder(node.right, list);
    }
}

