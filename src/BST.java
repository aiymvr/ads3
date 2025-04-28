import java.util.*;

/**
 * Binary Search Tree (BST) implementation .
 * - Supports insert (put), delete, search (get).
 * - Supports in-order traversal using a manual Stack.
 * - Key-value pairs are accessible during iteration.
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValue<K, V>> {

    // Private Node class
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
    private int size;  // Size of the BST (number of nodes)

    // Public KeyValue class for iteration
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
     * Inserts a key-value pair into the BST (iterative).
     */
    public void put(K key, V val) {
        Node newNode = new Node(key, val);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node parent = null;
        Node current = root;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val; // Update value if key exists
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    /**
     * Searches for a value by key (iterative).
     */
    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }

        return null;
    }

    /**
     * Deletes a node with the given key from the BST (iterative).
     */
    public void delete(K key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, K key) {
        Node parent = null;
        Node current = root;

        // Search for the node
        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return root; // Key not found
        }

        // Case 1: Node has no children
        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.left == current) parent.left = null;
                else parent.right = null;
            } else {
                root = null;
            }
        }
        // Case 2: Node has two children
        else if (current.left != null && current.right != null) {
            Node successor = findMin(current.right);
            K valKey = successor.key;
            V valVal = successor.val;
            delete(successor.key);
            current.key = valKey;
            current.val = valVal;
        }
        // Case 3: Node has one child
        else {
            Node child = (current.left != null) ? current.left : current.right;
            if (current != root) {
                if (current == parent.left) parent.left = child;
                else parent.right = child;
            } else {
                root = child;
            }
        }

        size--;
        return root;
    }

    /**
     * Helper method to find the node with the minimum key (iterative).
     */
    private Node findMin(Node node) {
        Node current = node;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Returns the number of key-value pairs in the BST.
     */
    public int size() {
        return size;
    }


    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        List<KeyValue<K, V>> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(new KeyValue<>(current.key, current.val));
            current = current.right;
        }

        return list.iterator();
    }
}


