public class MyHashTable<K, V> {

    // Inner class representing a single node in the hash table's chain
    public static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        // Constructor
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private HashNode<K, V>[] chainArray; // Array of chains (linked lists)
    private int M; // Number of buckets
    private int size; // Number of key-value pairs stored

    // Default constructor with default number of buckets
    public MyHashTable() {
        this(11);
    }

    // Constructor with custom number of buckets
    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    // Hash function to compute index for a key
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    // Inserts a key-value pair into the hash table
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        // Check if key already exists and update
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert new node at the beginning of chain
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }

    // Returns the value associated with the given key
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    // Removes a key-value pair from the hash table
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    chainArray[index] = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    // Checks if the value exists in the table
    public boolean contains(V value) {
        for (HashNode<K, V> head : chainArray) {
            while (head != null) {
                if (head.value.equals(value)) return true;
                head = head.next;
            }
        }
        return false;
    }

    // Returns the key for a given value
    public K getKey(V value) {
        for (HashNode<K, V> head : chainArray) {
            while (head != null) {
                if (head.value.equals(value)) return head.key;
                head = head.next;
            }
        }
        return null;
    }

    // Returns the number of elements in the table
    public int size() {
        return size;
    }

    // Returns the number of buckets
    public int getBucketCount() {
        return M;
    }

    // Returns number of elements in each bucket
    public int[] getBucketSizes() {
        int[] counts = new int[M];
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                counts[i]++;
                head = head.next;
            }
        }
        return counts;
    }

    // Helper method for testing
    public HashNode<K, V>[] getChainArray() {
        return chainArray;
    }
}
